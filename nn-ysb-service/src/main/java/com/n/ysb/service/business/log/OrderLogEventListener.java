package com.n.ysb.service.business.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.n.ysb.service.orderLog.mapper.NnOrderLogMapper;
import com.n.ysb.service.orderLog.po.NnOrderLog;

@Service
public class OrderLogEventListener implements ApplicationListener<OrderLogEvent> {
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private NnOrderLogMapper orderLogMapper;
	
	@Override
	public void onApplicationEvent(OrderLogEvent logEvent) {
		NnOrderLog orderLog = logEvent.getOrderLog();
		log.info("orderNo-{} start to log status change, -> {}", orderLog.getOrderNo(),  orderLog.getOrderStatus());
		int c = orderLogMapper.insertSelective(orderLog);
		log.info("orderNo-{} start to log status change, is successfull:{}", orderLog.getOrderNo(), c>0 ? true : false);
	}

}
