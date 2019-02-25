package com.n.ysb.service.business.pars;

import org.apache.commons.lang.StringUtils;

import com.n.ysb.service.util.MD5;

public class LoginPars extends BasePars {

	private String pwd;
	
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	@Override
	public String sign(String key) {
		String s = MD5.md5(this.getTimestamp() + this.getMerchantMobile() + pwd + key);
		return s;
	}
	
	@Override
	public boolean hasNull() {
		if(super.hasNull()) {
			return true;
		}
		if(StringUtils.isBlank(this.pwd)) {
			return true;
		}
		return false;
	}
	
}
