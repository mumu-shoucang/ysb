package com.n.ysb.service.business.callbackin;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n.ysb.service.business.BusRedis;
import com.n.ysb.service.business.OrderStatus;
import com.n.ysb.service.business.WithdrawStatus;
import com.n.ysb.service.business.callbackin.vo.PayBackVo;
import com.n.ysb.service.business.callbackin.vo.WithdrawBackVo;
import com.n.ysb.service.business.log.OrderStatusLog;
import com.n.ysb.service.order.mapper.NnOrderMapper;
import com.n.ysb.service.order.po.NnOrder;
import com.n.ysb.service.order.po.NnOrderExample;
import com.n.ysb.service.order.po.NnOrderExample.Criteria;
import com.n.ysb.service.withdraw.mapper.NnYeecustomerWithdrawLogMapper;
import com.n.ysb.service.withdraw.mapper.NnYeecustomerWithdrawMapper;
import com.n.ysb.service.withdraw.po.NnYeecustomerWithdraw;
import com.n.ysb.service.withdraw.po.NnYeecustomerWithdrawExample;
import com.n.ysb.service.withdraw.po.NnYeecustomerWithdrawLog;

@Service
public class CallbackService {

	private static final Logger log = LoggerFactory.getLogger(CallbackService.class);
	
	@Autowired
	private OrderStatusLog orderStatusLog;
	@Autowired
	private NnOrderMapper orderDao;
	@Autowired
	private BusRedis busRedis;
	
	@Autowired
	private NnYeecustomerWithdrawMapper withdrawMapper;
	
	@Autowired
	private NnYeecustomerWithdrawLogMapper withdrawLogMapper;
	
	
	public String payCallback(PayBackVo pars) {
		String requestId = pars.getRequestId();
		String externalld = pars.getExternalld();
		String payTime = pars.getPayTime();
		String fee = pars.getFee();
		String status = pars.getStatus();
		
		NnOrder order = new NnOrder();
		order.setYeeExternalLd(externalld);
		try {
			order.setYeePayDate(DateUtils.parseDate(payTime, new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"}));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		order.setYeePayStatus(status);
		order.setYeeTradeFee(new BigDecimal(fee));
		order.setOrderStatus(OrderStatus.pay_suc.getCode());
		
		String orderNo = requestId;
		NnOrderExample condition = new NnOrderExample();
		Criteria criteria = condition.createCriteria();
		criteria.andOrderNoEqualTo(orderNo);
		criteria.andOrderStatusNotEqualTo(OrderStatus.pay_suc.getCode());
		criteria.andOrderStatusNotEqualTo(OrderStatus.withdraw_suc.getCode());
		int c = orderDao.updateByExampleSelective(order, condition);
		if(c > 0) {
			orderStatusLog.logOrderPaySuc(orderNo, "接收到易宝侧的支付回调，更新单据状态为支付成功");
			// stat receive
			NnOrderExample condition2 = new NnOrderExample();
			Criteria criteria2 = condition2.createCriteria();
			criteria2.andOrderNoEqualTo(orderNo);
			List<NnOrder> orders = orderDao.selectByExample(condition2);
			busRedis.statByMobile(orders.get(0).getMerchantMobile(), orders.get(0).getOrderAmt());
		}
		return "SUCCESS";
	}
	
	public String withdrawCallback(WithdrawBackVo pars) {

		String externalNo = pars.getExternalNo();
		String serialNo = pars.getSerialNo();
		
		String yeeWithdrawHandleDate = pars.getHandleTime();
		String yeeWithdrawStatus = pars.getTransferStatus();
		String yeeT0WithdrawFee = pars.getBasicFee();
		String yeeT0WithdrawExfee = pars.getExTargetFee();
		String yeeReceiver = pars.getReceiver();
		String yeeReceiverBankcardNo = pars.getReceiverBankCardNo();
		String yeeWithdrawActualAmt = pars.getActualAmount();
		String yeeWithdrawAmt = pars.getAmount();
		
		NnOrder order = new NnOrder();
		order.setYeeReceiver(yeeReceiver);
		order.setYeeReceiverBankcardNo(yeeReceiverBankcardNo);
		order.setYeeT0WithdrawExfee(new BigDecimal(yeeT0WithdrawExfee));
		order.setYeeT0WithdrawFee(new BigDecimal(yeeT0WithdrawFee));
		order.setYeeWithdrawActualAmt(new BigDecimal(yeeWithdrawActualAmt));
		order.setYeeWithdrawAmt(new BigDecimal(yeeWithdrawAmt));
		try {
			order.setYeeWithdrawHandleDate(DateUtils.parseDate(yeeWithdrawHandleDate, new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"}));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		order.setYeeWithdrawStatus(yeeWithdrawStatus);
		order.setOrderStatus(OrderStatus.withdraw_suc.getCode());
		
		String orderNo = externalNo;
		NnOrderExample condition = new NnOrderExample();
		Criteria criteria = condition.createCriteria();
		criteria.andOrderNoEqualTo(orderNo);
		criteria.andOrderStatusNotEqualTo(OrderStatus.withdraw_suc.getCode());
		int c = orderDao.updateByExampleSelective(order, condition);
		if(c > 0) {
			orderStatusLog.logOrderWithdrawSuc(orderNo, "接收到易宝侧的结算回调，更新单据状态为结算成功");
		}
		
		return "SUCCESS";
	}
	
	public String merchantWithdrawCallback(WithdrawBackVo pars) {

        String externalNo = pars.getExternalNo();
        String serialNo = pars.getSerialNo();
        
        String yeeWithdrawHandleDate = pars.getHandleTime();
        String yeeWithdrawStatus = pars.getTransferStatus();
        String yeeT0WithdrawFee = pars.getBasicFee();
        String yeeT0WithdrawExfee = pars.getExTargetFee();
        String yeeReceiver = pars.getReceiver();
        String yeeReceiverBankcardNo = pars.getReceiverBankCardNo();
        String yeeWithdrawActualAmt = pars.getActualAmount();
        String yeeWithdrawAmt = pars.getAmount();
        
        NnYeecustomerWithdraw order = new NnYeecustomerWithdraw();
        order.setYeeReceiver(yeeReceiver);
        order.setYeeReceiverBankcardNo(yeeReceiverBankcardNo);
        order.setYeeT0WithdrawExfee(new BigDecimal(yeeT0WithdrawExfee));
        order.setYeeT0WithdrawFee(new BigDecimal(yeeT0WithdrawFee));
        order.setYeeWithdrawActualAmt(new BigDecimal(yeeWithdrawActualAmt));
        order.setYeeWithdrawAmt(new BigDecimal(yeeWithdrawAmt));
        order.setYeeExternalLd(serialNo);
        try {
            order.setYeeWithdrawHandleDate(DateUtils.parseDate(yeeWithdrawHandleDate, new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"}));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        order.setYeeWithdrawStatus(yeeWithdrawStatus);
        order.setWithdrawStatus(WithdrawStatus.withdraw_suc.getCode());
        
        String orderNo = externalNo;
        NnYeecustomerWithdrawExample condition = new NnYeecustomerWithdrawExample();
        com.n.ysb.service.withdraw.po.NnYeecustomerWithdrawExample.Criteria criteria = condition.createCriteria();
        criteria.andOrderNoEqualTo(orderNo);
        criteria.andWithdrawStatusNotEqualTo(WithdrawStatus.withdraw_suc.getCode());
        int c = withdrawMapper.updateByExampleSelective(order, condition);
        if(c > 0) {
            withdrawLog(orderNo, order.getWithdrawStatus(), "接收到易宝侧的子商户结算回调，更新单据状态为结算成功");
            
        }
        
        return "SUCCESS";
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
}
