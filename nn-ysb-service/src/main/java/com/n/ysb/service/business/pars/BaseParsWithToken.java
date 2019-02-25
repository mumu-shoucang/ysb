package com.n.ysb.service.business.pars;

import org.apache.commons.lang.StringUtils;

import com.n.ysb.service.business.pars.base.Sign;

/**
 * 接口基本参数
 */
public class BaseParsWithToken extends BasePars implements Sign {

	private String token;
	
	private boolean needCheckToken = true;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public boolean isNeedCheckToken() {
		return needCheckToken;
	}
	
	@Override
	public boolean hasNull() {
		if(super.hasNull()) {
			return true;
		}
		if(StringUtils.isBlank(this.token)) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "[" + this.getTimestamp() + "-" + this.getMerchantMobile() + "-" + this.getSign() + "-" + this.token + "]"; 
	}
	
}
