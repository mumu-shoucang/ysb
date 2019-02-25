package com.n.ysb.service.business.pars;

import org.apache.commons.lang.StringUtils;

import com.n.ysb.service.business.pars.base.AESString;
import com.n.ysb.service.business.pars.base.Sign;
import com.n.ysb.service.util.MD5;

/**
 * 接口基本参数
 */
public class BasePars implements Sign {

	private String timestamp;
	private String sign;
	private AESString merchantMobile;
	
	private boolean needCheckToken = false;
	
	public boolean isNeedCheckToken() {
		return needCheckToken;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public AESString getMerchantMobile() {
		return merchantMobile;
	}
	public void setMerchantMobile(AESString merchantMobile) {
		this.merchantMobile = merchantMobile;
	}
	
	@Override
	public String sign(String key) {
		return MD5.md5(timestamp + merchantMobile + key);
	}
	
	@Override
	public boolean hasNull() {
		if(StringUtils.isBlank(this.timestamp) || StringUtils.isBlank(this.sign) || this.merchantMobile == null || StringUtils.isBlank(this.merchantMobile.toString())) {
			return true;
		}
		return false;
	}
	
	@Override
	public String getToken() {
		return "";
	}
	
	@Override
	public String toString() {
		return "[" + this.timestamp + "-" + this.merchantMobile + "-" + this.sign + "]";
	}
	
}
