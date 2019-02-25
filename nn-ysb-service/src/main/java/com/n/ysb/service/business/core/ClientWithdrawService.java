package com.n.ysb.service.business.core;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.n.ysb.service.business.WithdrawStatus;
import com.n.ysb.service.business.core.YeepayBusService.YeeReturn;
import com.n.ysb.service.business.pars.QueryWithdrawPars;
import com.n.ysb.service.business.pars.WithdrawPars;
import com.n.ysb.service.business.pars.base.ReturnCode;
import com.n.ysb.service.business.pars.base.ReturnMap;
import com.n.ysb.service.merchant.mapper.NnMerchantMapper;
import com.n.ysb.service.merchant.po.NnMerchant;
import com.n.ysb.service.merchant.po.NnMerchantExample;
import com.n.ysb.service.merchant.vo.NnMerchantVo;
import com.n.ysb.service.order.po.NnOrder;
import com.n.ysb.service.thirdparty.vo.WithDrawVo;
import com.n.ysb.service.withdraw.mapper.NnYeecustomerWithdrawLogMapper;
import com.n.ysb.service.withdraw.mapper.NnYeecustomerWithdrawMapper;
import com.n.ysb.service.withdraw.po.NnYeecustomerWithdraw;
import com.n.ysb.service.withdraw.po.NnYeecustomerWithdrawExample;
import com.n.ysb.service.withdraw.po.NnYeecustomerWithdrawExample.Criteria;
import com.n.ysb.service.withdraw.po.NnYeecustomerWithdrawLog;

@Service
public class ClientWithdrawService {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private YeepayBusService yeepayBusService;
    
    @Autowired
    private IDGenerator IDGenerator;
    
    @Autowired
    private NnMerchantMapper merchantMapper;
    
    @Autowired
    private NnYeecustomerWithdrawLogMapper withdrawLogMapper;
    
    @Autowired
    private NnYeecustomerWithdrawMapper withdrawMapper;
    
    public Map<String, Object> withdraw(WithdrawPars pars){
        //创建订单
        NnYeecustomerWithdraw buildOrder = buildOrder(pars);
        
        boolean addSuc = addOrder(buildOrder);
        if(!addSuc) {
            return ReturnMap.fail();
        }
        
        withdrawLog(buildOrder.getOrderNo(),buildOrder.getWithdrawStatus(),"结算订单接收成功");
        
        NnMerchant merchant = getMerchant(pars.getMerchantMobile().toString());
        NnMerchantVo merchantVo = new NnMerchantVo();
        merchantVo.setYeeCustomerNumber(buildOrder.getYeeCustomerNumber());
        
        WithDrawVo withDrawVo = new WithDrawVo();
        withDrawVo.setWithDrawAmt(buildOrder.getWithdrawAmt());
        withDrawVo.setExternalNo(buildOrder.getOrderNo());
        
        YeeReturn yeeRet = yeepayBusService.withdraw(merchantVo, withDrawVo);
        
        if(!yeeRet.isSuc()) {
            withdrawLog(buildOrder.getOrderNo(),buildOrder.getWithdrawStatus(), "调用易宝结算接口返回信息为：" + yeeRet.getDesc());
            return ReturnMap.New(ReturnCode.bus_invalid.getCode(), yeeRet.getDesc());
        }
        
        withdrawLog(buildOrder.getOrderNo(),buildOrder.getWithdrawStatus(),"成功调用易宝侧withdraw接口");
        
        return ReturnMap.suc();
        
    }
    
    private boolean addOrder(NnYeecustomerWithdraw order) {
        int c = withdrawMapper.insertSelective(order);
        return c > 0 ? true : false;
    }
    
    private NnMerchant getMerchant(String merchantMobile){
        NnMerchantExample condition = new NnMerchantExample();
        com.n.ysb.service.merchant.po.NnMerchantExample.Criteria criteria = condition.createCriteria();
        criteria.andLoginMobileEqualTo(merchantMobile);
        List<NnMerchant> merchants = merchantMapper.selectByExample(condition);
        
        return merchants != null && merchants.size() == 1 ? merchants.get(0) : null;
    }
    
    private NnYeecustomerWithdraw buildOrder(WithdrawPars pars) {
        NnYeecustomerWithdraw po = new NnYeecustomerWithdraw();
        po.setCreateDate(new Date());
        po.setYeeCustomerNumber(pars.getYeeCustomerNumber());
        po.setWithdrawAmt(pars.getWithdrawAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
        po.setOrderNo(IDGenerator.buildWithdrawOrderNo());
        po.setWithdrawStatus(WithdrawStatus.init.getCode());
        
        return po;
    }
    
    private void withdrawLog(String orderNo, String withdrawStatus, String desc){
        NnYeecustomerWithdrawLog log = new NnYeecustomerWithdrawLog();
        log.setOrderNo(orderNo);
        log.setOpTime(new Date());
        log.setOpUser("system");
        log.setWithdrawStatus(withdrawStatus);
        log.setRemarks(desc);
        
        withdrawLogMapper.insertSelective(log);
    }

    public Map<String, Object> queryWithdraw(QueryWithdrawPars pars, int pageNo, int limit) {
        Page<NnYeecustomerWithdraw> startPage = PageHelper.startPage(pageNo, limit);
        NnYeecustomerWithdrawExample example = new NnYeecustomerWithdrawExample();
        Criteria criteria = example.createCriteria();
        criteria.andYeeCustomerNumberEqualTo(pars.getYeeCustomerNumber());
        
        example.setOrderByClause("CREATE_DATE desc");
        
        List<NnYeecustomerWithdraw> WithdrawOrderList = withdrawMapper.selectByExample(example);
        
        return ReturnMap.suc(startPage.getResult());
    }
    
    
}
