package com.n.ysb.service.business.pars;

import org.apache.commons.lang.StringUtils;

import com.n.ysb.service.util.MD5;

public class RegisterPars extends BasePars {

	private String refCode;
	private String pwd;
	private String smsCode;
	
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	public String getRefCode() {
		return refCode;
	}
	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}
	
	@Override
	public String sign(String key) {
		String s = MD5.md5(this.getTimestamp() + this.getMerchantMobile() + refCode + pwd + smsCode + key);
		return s;
	}
	
	@Override
	public boolean hasNull() {
		if(super.hasNull()) {
			return true;
		}
		if(StringUtils.isBlank(this.refCode) || StringUtils.isBlank(pwd) || StringUtils.isBlank(smsCode)) {
			return true;
		}
		return false;
	}
	
}
