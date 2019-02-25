package com.n.ysb.service.thirdparty.sms;

public interface SMS2 {

	/** 发送6位短信验证码 */
	void sendSMSCode(String mobile, String smsCode);

    String balanceQuery();
}
