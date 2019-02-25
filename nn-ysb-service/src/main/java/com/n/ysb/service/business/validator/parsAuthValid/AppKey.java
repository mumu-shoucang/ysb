package com.n.ysb.service.business.validator.parsAuthValid;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AppKey {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Value("${md5.key}")
	private String md5key;
	@Value("${aes.key}")
	private String aesKey;
	@Value("${request.valid.sec}")
	private String requestValidSec;
	@Value("${h5.register.sms}")
    private String registerMsg;
	
	@PostConstruct
	private void init() {
		log.info("appKey init...");
		log.info("appKey init {}-{}", requestValidSec, registerMsg);
	}

	public String getMd5key() {
		return md5key;
	}
	public void setMd5key(String md5key) {
		this.md5key = md5key;
	}
	public String getAesKey() {
		return aesKey;
	}
	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}
	public String getRequestValidSec() {
		return requestValidSec;
	}
	public void setRequestValidSec(String requestValidSec) {
		this.requestValidSec = requestValidSec;
	}
	public String getRegisterMsg() {
		return registerMsg;
	}
	public void setRegisterMsg(String registerMsg) {
		this.registerMsg = registerMsg;
	}
	
}
