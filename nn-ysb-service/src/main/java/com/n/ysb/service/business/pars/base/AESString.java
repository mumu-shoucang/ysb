package com.n.ysb.service.business.pars.base;

/**
 * 自定义数据类型，需要AES解密
 */
public class AESString {

	private String value;
	
	public AESString() {
		this.value = "";
	}
	
	public AESString(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
