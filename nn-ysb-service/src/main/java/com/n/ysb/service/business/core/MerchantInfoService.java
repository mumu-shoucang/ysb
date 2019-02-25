package com.n.ysb.service.business.core;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.n.ysb.service.business.BusRedis;
import com.n.ysb.service.business.core.YeepayBusService.YeeReturn;
import com.n.ysb.service.business.pars.AddCreditCardPars;
import com.n.ysb.service.business.pars.CompleteMerchantPars;
import com.n.ysb.service.business.pars.GetCreditCardPars;
import com.n.ysb.service.business.pars.GetMerchantInfoPars;
import com.n.ysb.service.business.pars.RemoveCreditCardPars;
import com.n.ysb.service.business.pars.UpdateMerchantBankAccountPars;
import com.n.ysb.service.business.pars.base.ReturnCode;
import com.n.ysb.service.business.pars.base.ReturnMap;
import com.n.ysb.service.merchant.mapper.NnMerchantMapper;
import com.n.ysb.service.merchant.po.NnMerchant;
import com.n.ysb.service.merchant.po.NnMerchantExample;
import com.n.ysb.service.merchant.vo.NnMerchantBaseVo;
import com.n.ysb.service.merchantCreditCard.mapper.NnMerchantCreditCardMapper;
import com.n.ysb.service.merchantCreditCard.po.NnMerchantCreditCard;
import com.n.ysb.service.merchantCreditCard.po.NnMerchantCreditCardExample;
import com.n.ysb.service.merchantCreditCard.po.NnMerchantCreditCardExample.Criteria;
import com.n.ysb.service.merchantCreditCard.vo.NnMerchantCreditCardVo;
import com.n.ysb.service.referrer.mapper.NnReferrerMapper;
import com.n.ysb.service.referrer.po.NnReferrer;
import com.n.ysb.service.referrer.po.NnReferrerExample;
import com.n.ysb.service.thirdparty.yeepay.IYeePayRequestService;

@Service
public class MerchantInfoService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IDGenerator IDGenerator;
	@Autowired
	private NnMerchantMapper merchantDao;
	@Autowired
	private NnMerchantCreditCardMapper merchantCreditCardDao;
	@Autowired
	private DebitBankService debitBankService;
	@Autowired
	private YeepayBusService yeepayBusService;
	@Autowired
	private IYeePayRequestService yeePayRequestService;
	@Autowired
	private CreditBankService creditBankService;
	@Autowired
	private BankCodeName bankCodeName;
	@Autowired
	private BusRedis busRedis;
	@Autowired
	private NnReferrerMapper referrerMapper;
	
	public Map<String, Object> addCreditCard(AddCreditCardPars pars) {
		String cardNo = pars.getCardNo();
		String mobile = pars.getMerchantMobile().toString();
		String bindMobile = pars.getBindMobile().toString();
		
		// check sms code
		if(!busRedis.getSmsCode(bindMobile).equals(pars.getSmsCode())) {
			return ReturnMap.New(ReturnCode.bus_invalid.getCode(), "短信验证码非法");
		}
		
		NnMerchantCreditCard card = new NnMerchantCreditCard();
		card.setMerchantMobile(mobile);
		card.setCardNo(cardNo);
		
		// check bank type
		String bankCode = creditBankService.getCCBankCodeFromAli(cardNo);
		if(StringUtils.isBlank(bankCode)) {
			return ReturnMap.New(ReturnCode.bus_invalid.getCode(), "非信用卡类型");
		}
		
		if(StringUtils.isBlank(pars.getBankCode())) {
			card.setBankCode(bankCode);
		}
		if(StringUtils.isBlank(pars.getBankName())) {
			card.setBankName(bankCodeName.getNameByCode(card.getBankCode()));
		}
		card.setCardCode(IDGenerator.buildCreditCardCode());
		
		try{
			int c = merchantCreditCardDao.insertSelective(card);
			if(c != 1){
				return ReturnMap.fail();
			}
			return ReturnMap.suc();
		} catch(Exception ex) {
			return ReturnMap.New(ReturnCode.bus_invalid.getCode(), "信用卡号已绑定");
		}
	}
	
	public Map<String, Object> getCreditCards(GetCreditCardPars pars) {
		String mobile = pars.getMerchantMobile().toString();
		
		NnMerchantCreditCardExample condition = new NnMerchantCreditCardExample();
		Criteria criteria = condition.createCriteria();
		criteria.andMerchantMobileEqualTo(mobile);
		List<NnMerchantCreditCard> creditCards = merchantCreditCardDao.selectByExample(condition);
		
		List<NnMerchantCreditCardVo> creditCardVos = ListHelper.copyTo(creditCards, NnMerchantCreditCardVo.class);
		// hide cardNo
		for(NnMerchantCreditCardVo cc : creditCardVos) {
			String bankNo = cc.getCardNo();
			cc.setCardNo(NoFormat.formatBankNo(bankNo));
			cc.setShowTag(cc.getBankName() + "(" + bankNo.substring(bankNo.length()-4) + ")");
		}
		
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("cards", creditCardVos);
		return ReturnMap.suc(ret);
	}
	
	public Map<String, Object> removeCreditCard(RemoveCreditCardPars pars) {
		String mobile = pars.getMerchantMobile().toString();
		String cardCode = pars.getCardCode();
		
		NnMerchantCreditCardExample condition = new NnMerchantCreditCardExample();
		Criteria criteria = condition.createCriteria();
		criteria.andMerchantMobileEqualTo(mobile);
		criteria.andCardCodeEqualTo(cardCode);
		int c = merchantCreditCardDao.deleteByExample(condition);
		
		if(c != 1){
			return ReturnMap.New(ReturnCode.bus_invalid.getCode(), "信用卡编码有误");
		}
		return ReturnMap.suc();
	}
	
	@Transactional
	public Map<String, Object> completeMerchantInfo(CompleteMerchantPars pars,  File bankCardPhoto, File idCardPhoto, File idCardBackPhoto, File personPhoto) {
		String bankAccountName = pars.getBankAccountName();
		String bankAccountNo = pars.getBankAccountNo();
		
		String bankName = debitBankService.matchBank(bankAccountNo);
		if(StringUtils.isEmpty(bankName)) {
			return ReturnMap.New(ReturnCode.bus_invalid.getCode(), "不支持的银行卡");
		};
		
		String idCard = pars.getIdCard();
		Date idCardStart = pars.getIdCardStart();
		Date idCardEnd = pars.getIdCardEnd();
		String mobile = pars.getMerchantMobile().toString();
		String bindMobile = "";
		if(pars.getBindMobile() == null) {
			bindMobile = mobile;
		}
		
		NnMerchant merchant = new NnMerchant();
		merchant.setBankAccountName(bankAccountName);
		merchant.setBankAccountNo(bankAccountNo);
		merchant.setBankName(bankName);
		merchant.setBindMobile(bindMobile);
		merchant.setLoginMobile(mobile);
		merchant.setMerchantCode(IDGenerator.buildMerchantCode());
		
		merchant.setIdCard(idCard);
		merchant.setIdCardStart(idCardStart);
		merchant.setIdCardEnd(idCardEnd);
		
		
		YeeReturn yeeRet = yeepayBusService.register(merchant, bankCardPhoto, idCardPhoto, idCardBackPhoto, personPhoto);
		
		if(!yeeRet.isSuc()) {
			return ReturnMap.New(ReturnCode.bus_invalid.getCode(), yeeRet.getDesc());
		}
		
		merchant.setYeeCustomerNumber(yeeRet.getBody());
		//增加商户认证时间
		merchant.setAuthDate(new Date());
		
		try {
			NnMerchantExample condition = new NnMerchantExample();
			com.n.ysb.service.merchant.po.NnMerchantExample.Criteria criteria = condition.createCriteria();
			criteria.andLoginMobileEqualTo(mobile);
			int cc = merchantDao.updateByExampleSelective(merchant, condition);
			if(cc != 1){
				// do update again; try 2 times, then return fail
				cc = merchantDao.updateByExampleSelective(merchant, condition);
				if(cc != 1) {
					return ReturnMap.New(ReturnCode.fail.getCode(), "更新商户信息异常，请重试");
				}
			}
			
			//更新推荐人，2018.09.06添加
			NnReferrer referrer = getReferrerByIdCard(idCard);
			if(referrer!=null){
			    log.info("注册用户后,发现推荐人，开始更新推荐人的子商户编码");
			    referrer.setYeeCustomerNumber(yeeRet.getBody());
			    NnReferrerExample example = new NnReferrerExample();
			    com.n.ysb.service.referrer.po.NnReferrerExample.Criteria criteria2 = example.createCriteria();
			    criteria2.andIdCardEqualTo(idCard);
			    
			    int referCc = referrerMapper.updateByExampleSelective(referrer, example);
			    if(referCc != 1){
			        return ReturnMap.New(ReturnCode.fail.getCode(), "更新推荐人信息异常，请重试");
			    }
			}
		} catch(Exception ex) {
			log.error("商户认证信息更新失败", ex);
			return ReturnMap.New(ReturnCode.bus_invalid.getCode(), "此商户已认证");
		}
		
		// do set fee
		new Thread(yeepayBusService.FeesetProcessor(merchant)).start();
		
		return ReturnMap.suc();
	}
	
	public Map<String, Object> updateMerchantBankAccount(UpdateMerchantBankAccountPars pars) {
		String bankAccountName = pars.getBankAccountName();
		String bankAccountNo = pars.getBankAccountNo();
		String mobile = pars.getMerchantMobile().toString();
		String bindMobile = pars.getBindMobile().toString();
		
		// check sms code
		if(!busRedis.getSmsCode(bindMobile).equals(pars.getSmsCode())) {
			return ReturnMap.New(ReturnCode.bus_invalid.getCode(), "短信验证码非法");
		}
		
		String bankName = debitBankService.matchBank(bankAccountNo);
		if(StringUtils.isEmpty(bankName)) {
			return ReturnMap.New(ReturnCode.bus_invalid.getCode(), "不支持的银行卡");
		};
		
		NnMerchantExample condition = new NnMerchantExample();
		com.n.ysb.service.merchant.po.NnMerchantExample.Criteria criteria = condition.createCriteria();
		criteria.andLoginMobileEqualTo(mobile);
		 List<NnMerchant> lists = merchantDao.selectByExample(condition);
		 if(lists == null || lists.size() != 1) {
			 return ReturnMap.New(ReturnCode.bus_invalid.getCode(), "参数错误");
		 }
		
		NnMerchant merchant = new NnMerchant();
		merchant.setBankAccountName(bankAccountName);
		merchant.setBankAccountNo(bankAccountNo);
		merchant.setBankName(bankName);
		
		// update yeepay's info
		merchant.setYeeCustomerNumber(lists.get(0).getYeeCustomerNumber());
		YeeReturn yeeRet = yeepayBusService.updateCustomerBankInfo(merchant);
		if(!yeeRet.isSuc()) {
			return ReturnMap.New(ReturnCode.bus_invalid.getCode(), yeeRet.getDesc());
		}
		
		int c = merchantDao.updateByExampleSelective(merchant, condition);
		if(c != 1){
			return ReturnMap.fail();
		}
		
		return ReturnMap.suc();
	}
	
	public Map<String, Object> getMerchantInfo(GetMerchantInfoPars pars) {
		String mobile = pars.getMerchantMobile().toString();
		
		NnMerchantExample condition = new NnMerchantExample();
		com.n.ysb.service.merchant.po.NnMerchantExample.Criteria criteria = condition.createCriteria();
		criteria.andLoginMobileEqualTo(mobile);
		List<NnMerchant> merchants = merchantDao.selectByExample(condition);
		if(merchants == null || merchants.size() != 1) {
			return ReturnMap.New(ReturnCode.bus_invalid.getCode(), "参数错误");
		}
		
		Map<String, Object> ret = new HashMap<String, Object>();
		NnMerchantBaseVo vo = new NnMerchantBaseVo();
		BeanUtils.copyProperties(merchants.get(0), vo);
		
		//query referrer by idCard
		if(StringUtils.isNotBlank(vo.getIdCard())){
		    NnReferrer referrer = getReferrerByIdCard(vo.getIdCard());
		    vo.setIsReferrer(referrer!=null);
		}
		
		
		// hide vip info
		vo.setBankAccountNo(NoFormat.formatBankNo(vo.getBankAccountNo()));
		vo.setIdCard(NoFormat.formatIdcard(vo.getIdCard()));
		
		ret.put("info",  vo);
		return ReturnMap.suc(ret);
	}
	
	private NnReferrer getReferrerByIdCard(String idCard){
	    NnReferrerExample example = new NnReferrerExample();
	    com.n.ysb.service.referrer.po.NnReferrerExample.Criteria criteria = example.createCriteria();
	
	    criteria.andIdCardEqualTo(idCard);
	    
	    List<NnReferrer> referrerList = referrerMapper.selectByExample(example);
	    
	    return referrerList.size() == 1 ? referrerList.get(0) : null;
	}
	
}
