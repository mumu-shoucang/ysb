package com.n.ysb.service.business.core;

import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n.ysb.service.business.BusRedis;
import com.n.ysb.service.business.pars.base.ReturnCode;
import com.n.ysb.service.business.pars.base.ReturnMap;
import com.n.ysb.service.thirdparty.sms.SMS2;

@Service
public class SMSService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SMS2 sms2;
	
	@Autowired
	private BusRedis busRedis;
	
	public Map<String, Object> sendSMSCode(String mobile, String smsDesc) {
		
		log.info("send smsCode for moible {}", mobile);
		
		boolean cansend = busRedis.canSendSmsCode(mobile);
		if(!cansend) {
			return ReturnMap.New(ReturnCode.bus_invalid.getCode(), "24小时内发送短信验证码超限");
		}
		
		String smsCode = buildSmsCode();
		String msg = "短信验证码:" + smsCode + "，5分钟内有效。";
		if(StringUtils.isNotBlank(smsDesc)) {
			msg += smsDesc;
		}
		sms2.sendSMSCode(mobile, msg);
		busRedis.setSmsCode(mobile, smsCode);
		return ReturnMap.suc();
	}
	
	private String buildSmsCode(){
	    String smsCode ="";
	    while(true){
	        smsCode = new Random().nextInt(1000000) + "";
	        if(smsCode.length()==6){
	            break;
	        }
	    }
	    
	    return smsCode;
	}
	
}
