package com.n.ysb.service.business.log;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.n.ysb.service.business.OrderStatus;
import com.n.ysb.service.orderLog.po.NnOrderLog;

@Service
public class OrderStatusLog {

	@Autowired
	private ApplicationContext appContext;
	
	public void logOrderInit(String orderNo, String desc) {
		logOrderStatus(orderNo, OrderStatus.init, desc);
	}
	
	public void logOrderPaying(String orderNo, String desc) {
		logOrderStatus(orderNo, OrderStatus.paying, desc);
	}
	
	public void logOrderPaySuc(String orderNo, String desc) {
		logOrderStatus(orderNo, OrderStatus.pay_suc, desc);
	}
	
	public void logOrderWithdrawSuc(String orderNo, String desc) {
		logOrderStatus(orderNo, OrderStatus.withdraw_suc, desc);
	}
	
	public void logOrderStatus(String orderNo, OrderStatus orderStatus, String desc) {
		NnOrderLog orderLog = new NnOrderLog();
		orderLog.setOpUser("system");
		orderLog.setOpTime(new Date());
		orderLog.setOrderNo(orderNo);
		orderLog.setOrderStatus(orderStatus.getCode());
		orderLog.setRemarks(desc);
		appContext.publishEvent(new OrderLogEvent(this, orderLog));
	}
	
}
