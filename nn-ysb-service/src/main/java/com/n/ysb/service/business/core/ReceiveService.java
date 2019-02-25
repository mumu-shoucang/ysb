package com.n.ysb.service.business.core;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.n.ysb.service.business.OrderStatus;
import com.n.ysb.service.business.core.RecieveValidator.ValidateCode;
import com.n.ysb.service.business.core.YeepayBusService.YeeReturn;
import com.n.ysb.service.business.log.OrderStatusLog;
import com.n.ysb.service.business.pars.ComputeFeePars;
import com.n.ysb.service.business.pars.QueryReceivePars;
import com.n.ysb.service.business.pars.ReceivePars;
import com.n.ysb.service.business.pars.base.ReturnCode;
import com.n.ysb.service.business.pars.base.ReturnMap;
import com.n.ysb.service.globalCfg.IGlobalCfgService;
import com.n.ysb.service.globalCfg.po.NnGlobalCfg;
import com.n.ysb.service.globalCfg.vo.NnGlobalCfgVo;
import com.n.ysb.service.merchant.mapper.NnMerchantMapper;
import com.n.ysb.service.merchant.po.NnMerchant;
import com.n.ysb.service.merchant.po.NnMerchantExample;
import com.n.ysb.service.merchantCreditCard.mapper.NnMerchantCreditCardMapper;
import com.n.ysb.service.merchantCreditCard.po.NnMerchantCreditCard;
import com.n.ysb.service.merchantCreditCard.po.NnMerchantCreditCardExample;
import com.n.ysb.service.merchantCreditCard.po.NnMerchantCreditCardExample.Criteria;
import com.n.ysb.service.order.mapper.NnOrderMapper;
import com.n.ysb.service.order.po.NnOrder;
import com.n.ysb.service.order.po.NnOrderExample;
import com.n.ysb.service.referrer.mapper.NnReferrerMapper;
import com.n.ysb.service.referrer.po.NnReferrer;
import com.n.ysb.service.referrer.po.NnReferrerExample;

/**
 * 收款服务
 */
@Service
public class ReceiveService {

	@Autowired
	private OrderStatusLog orderStatusLog;
	@Autowired
	private NnOrderMapper orderDao;
	@Autowired
	private NnMerchantMapper merchantDao;
	@Autowired
	private NnMerchantCreditCardMapper merchantCreditCardDao;
	@Autowired
	private IDGenerator IDGenerator;
	@Autowired
	private RecieveValidator validator;
	@Autowired
	private YeepayBusService yeepayBusService;
	@Autowired
	private NnReferrerMapper referrerDao;
	@Autowired
	private BankCodeName bankCodeName;
	@Autowired
	private IGlobalCfgService globalCfgService;
	
	public Map<String, Object> getFee() {
		NnGlobalCfg cfg = validator.getCfg();
		
		Map<String, Object> ret = new HashMap<>();
		ret.put("tradeFee", cfg.getTradeFee());
		ret.put("T0WithdrawFee", cfg.getT0WithdrawFee());
		ret.put("T0WithdrawWorkdayFee", cfg.getT0WithdrawWorkdayFee());
		ret.put("T0WithdrawNonworkdayFee", cfg.getT0WithdrawNonworkdayFee());
		return ReturnMap.suc(ret);
	}
	
	public Map<String, Object> computeFee(ComputeFeePars pars) {
		Map<String, Object> ret = validator.computeFee(pars.getOrderAmt());
		return ReturnMap.suc(ret);
	}
	
	public Map<String, Object> receive(ReceivePars pars) {
		
		ValidateCode vc = validator.validate(pars);
		if(!vc.isSuc()) {
			String code = vc.getCode();
			String desc = vc.getDesc();
			return ReturnMap.New(code, desc);
		}
		
		// prepare && add fields
		NnOrder order = buildOrder(pars);
		
		// add fields
		NnMerchant merchant = computedMerchantInfo(order, pars.getMerchantMobile().toString());
		if(merchant == null) {
			return ReturnMap.New(ReturnCode.bus_invalid.getCode(), "参数错误");
		}
		
		// into db
		boolean addSuc = addOrder(order);
		if(!addSuc) {
			return ReturnMap.fail();
		}
		
		// log
		orderStatusLog.logOrderInit(order.getOrderNo(), "订单接收成功");
		YeeReturn yeeRet = yeepayBusService.receive(merchant, order);
		
		if(!yeeRet.isSuc()) {
			return ReturnMap.New(ReturnCode.bus_invalid.getCode(), yeeRet.getDesc());
		}
		orderStatusLog.logOrderInit(order.getOrderNo(), "成功调用易宝侧receive接口");
		return ReturnMap.receiveSuc(yeeRet.getBody());
	}
	
	public Map<String, Object> queryReceive(QueryReceivePars pars, int pageNo, int limit) {
		
		Page<NnOrder> startPage = PageHelper.startPage(pageNo, limit);
		String merchantMobile = pars.getMerchantMobile().toString();
		
		NnOrderExample condition = new NnOrderExample();
		com.n.ysb.service.order.po.NnOrderExample.Criteria criteria = condition.createCriteria();
		criteria.andMerchantMobileEqualTo(merchantMobile);
		condition.setOrderByClause("CREATE_DATE desc");
		List<NnOrder> orders = orderDao.selectByExample(condition);
		
		// cc bankName
		NnMerchantCreditCardExample condition2 = new NnMerchantCreditCardExample();
		com.n.ysb.service.merchantCreditCard.po.NnMerchantCreditCardExample.Criteria criteria2 = condition2.createCriteria();
		criteria2.andMerchantMobileEqualTo(merchantMobile);
		List<NnMerchantCreditCard> creditCards = merchantCreditCardDao.selectByExample(condition2);
		
		PageInfo<NnOrder> page = startPage.toPageInfo();
		// hide vip info
		for(NnOrder order : page.getList()) {
			order.setDebitCardNo(NoFormat.formatBankNo(order.getDebitCardNo()));
			String ccNo = order.getCreditCardNo();
			if(StringUtils.isNotBlank(ccNo)) {
				for(NnMerchantCreditCard cc : creditCards) {
					if(cc.getCardNo().equals(ccNo)) {
						order.setCreditCardNo(cc.getBankName() + "("+ccNo.substring(ccNo.length()-4)+")");
					}
				}
			}
		}
		
		return ReturnMap.suc(startPage.toPageInfo());
	}
	
		
	private NnOrder buildOrder(ReceivePars pars) {
		String merchantMobile = pars.getMerchantMobile().toString();
		NnOrder po = new NnOrder();
		po.setCreateDate(new Date());
		
		if(StringUtils.isNotBlank(pars.getCreditCardCode()))
			po.setCreditCardNo(getCreditCardNo(merchantMobile, pars.getCreditCardCode()));
		
		po.setMerchantMobile(merchantMobile);
		po.setOrderAmt(pars.getOrderAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
		po.setOrderNo(IDGenerator.buildOrderNo());
		po.setOrderStatus(OrderStatus.init.getCode());
		po.setMcc(pars.getMcc());
		
		// fee
		po.setTradeFee(pars.getTradeFee());
		po.setT0WithdrawFee(pars.getT0WithdrawFee());
		po.setT0WithdrawExfee(pars.getT0WithdrawEXFee());
		
		return po;
	}
	
	private NnMerchant computedMerchantInfo(NnOrder po, String merchantMobile) {
		NnMerchantExample condition = new NnMerchantExample();
		com.n.ysb.service.merchant.po.NnMerchantExample.Criteria criteria = condition.createCriteria();
		criteria.andLoginMobileEqualTo(merchantMobile);
		List<NnMerchant> merchants = merchantDao.selectByExample(condition);
		if(merchants != null && merchants.size() == 1){
			String debitCardNo = merchants.get(0).getBankAccountNo();
			String refSign = merchants.get(0).getRefSign();
			
			//佣金计算
			commisstionCalcu(po, refSign);
			
			po.setDebitCardNo(debitCardNo);
			po.setRefSign(refSign);
			return merchants.get(0);
		}
		return null;
	}
	
	private void commisstionCalcu(NnOrder po, String refSign){
	    //queryReferrer by refSign and calculate commisstion
        NnReferrerExample example = new NnReferrerExample();
        com.n.ysb.service.referrer.po.NnReferrerExample.Criteria criteria2 = example.createCriteria();
        criteria2.andRefSignEqualTo(refSign);
        List<NnReferrer> referrers = referrerDao.selectByExample(example);
        
        //查询全局设置
        NnGlobalCfgVo globalCfg = globalCfgService.getGlobalCfg();
        
        if(referrers !=null && referrers.size() == 1){
            //佣金比例
            BigDecimal commissionRate = referrers.get(0).getCommissionRate().divide(new BigDecimal(100)).setScale(6,BigDecimal.ROUND_HALF_UP);
            //基础佣金
            BigDecimal basicCommisstion = referrers.get(0).getBasicCommission();
            //订单金额
            BigDecimal orderAmt = po.getOrderAmt();
            
            BigDecimal tradeCommisstion = orderAmt.subtract(globalCfg.getT0WithdrawFee()).multiply(commissionRate).setScale(2,BigDecimal.ROUND_HALF_UP);
            
            BigDecimal commisstion = tradeCommisstion.add(basicCommisstion).setScale(2,BigDecimal.ROUND_HALF_UP);
            po.setTradeCommisstion(tradeCommisstion);
            po.setWithdrawCommisstion(basicCommisstion);
            po.setCommisstion(commisstion);
        }
	}
	
	private String getCreditCardNo(String merchantMobile, String creditCardCode) {
		NnMerchantCreditCardExample condition = new NnMerchantCreditCardExample();
		Criteria criteria = condition.createCriteria();
		criteria.andMerchantMobileEqualTo(merchantMobile);
		criteria.andCardCodeEqualTo(creditCardCode);
		List<NnMerchantCreditCard> merchantCreditCards = merchantCreditCardDao.selectByExample(condition);
		if(merchantCreditCards != null && merchantCreditCards.size() == 1){
			String creditCardNo = merchantCreditCards.get(0).getCardNo();
			return creditCardNo;
		}
		return null;
	}
	
	private boolean addOrder(NnOrder order) {
		int c = orderDao.insertSelective(order);
		return c > 0 ? true : false;
	}
	
}
