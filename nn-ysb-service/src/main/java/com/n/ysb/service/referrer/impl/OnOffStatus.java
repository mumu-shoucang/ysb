package com.n.ysb.service.referrer.impl;

public enum OnOffStatus {
	
	enable("1","启用"), disable("0","禁用");
	
	private String key;
	private String value;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	private OnOffStatus(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
}
