package com.n.ysb.service.business.pars;

import org.apache.commons.lang.StringUtils;

import com.n.ysb.service.util.MD5;

public class UpdatePWDPars extends BaseParsWithToken {

	private String pwd;
	private String newPwd;
	
	
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	@Override
	public String sign(String key) {
		return MD5.md5(this.getTimestamp() + this.getMerchantMobile() + this.newPwd + key);
	}
	
	@Override
	public boolean hasNull() {
		if(super.hasNull()) {
			return true;
		}
		if(StringUtils.isBlank(this.pwd) || StringUtils.isBlank(newPwd)) {
			return true;
		}
		return false;
	}
}
