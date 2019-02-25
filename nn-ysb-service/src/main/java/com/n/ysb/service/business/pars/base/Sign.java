package com.n.ysb.service.business.pars.base;

public interface Sign {

	String sign(String key);
	String getSign();
	String getTimestamp();
	AESString getMerchantMobile();
	
	boolean isNeedCheckToken();
	String getToken();
	
	boolean hasNull();
}
