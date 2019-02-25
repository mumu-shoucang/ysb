package com.n.ysb.service.business.pars;

import org.apache.commons.lang.StringUtils;

import com.n.ysb.service.util.MD5;

public class ForgetPWDPars extends BasePars {

	private String smsCode;
	private String newPwd;
	
	
	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	@Override
	public String sign(String key) {
		return MD5.md5(this.getTimestamp() + this.getMerchantMobile() + this.smsCode + this.newPwd + key);
	}
	
	@Override
	public boolean hasNull() {
		if(super.hasNull()) {
			return true;
		}
		if(StringUtils.isBlank(this.smsCode) || StringUtils.isBlank(this.newPwd)) {
			return true;
		}
		return false;
	}
}
