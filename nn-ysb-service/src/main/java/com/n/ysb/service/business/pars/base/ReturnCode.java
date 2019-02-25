package com.n.ysb.service.business.pars.base;

public enum ReturnCode {

	suc("0000", "成功"),
	fail("1111", "处理异常"),
	pars_auth_invalid("0001", "参数基本校验／认证失败"),
	bus_invalid("0002", "业务校验失败"),
	net_error("0003", "网络异常，请稍后重试"),
	token_invalid("0004", "token 非法");
	
	private String code;
	private String desc;
	private ReturnCode(String code, String desc) {
		this.setCode(code);
		this.setDesc(desc);
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
