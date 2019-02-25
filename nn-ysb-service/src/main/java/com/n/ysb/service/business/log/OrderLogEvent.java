package com.n.ysb.service.business.log;

import org.springframework.context.ApplicationEvent;

import com.n.ysb.service.orderLog.po.NnOrderLog;


public class OrderLogEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private NnOrderLog orderLog;
	
	public OrderLogEvent(Object source, NnOrderLog orderLog) {
		super(source);
		this.orderLog = orderLog;
	}

	public NnOrderLog getOrderLog() {
		return orderLog;
	}

	public void setOrderLog(NnOrderLog orderLog) {
		this.orderLog = orderLog;
	}

}
