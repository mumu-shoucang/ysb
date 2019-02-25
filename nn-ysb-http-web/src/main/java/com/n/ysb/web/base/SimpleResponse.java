package com.n.ysb.web.base;

import java.util.HashMap;
import java.util.Map;


public class SimpleResponse {
	
	private RspStatus status;
	private String desc;
	private Object body;
	
	private final long timestamp = System.currentTimeMillis();
	
	public static SimpleResponse suc(Object body) {
		return new SimpleResponse(RspStatus.SUCCESS, "success", body);
	}
	
	public static SimpleResponse sucWithDesc(String desc, Object body) {
		return new SimpleResponse(RspStatus.SUCCESS, desc, body);
	}
	
	public static SimpleResponse sucOnlyDesc(String desc) {
		return new SimpleResponse(RspStatus.SUCCESS, desc, new HashMap<>());
	}
	
	public static SimpleResponse fail(Object body) {
		return new SimpleResponse(RspStatus.FAIL, "", body);
	}
	
	public static SimpleResponse failWithDesc(String desc, Object body) {
		return new SimpleResponse(RspStatus.FAIL, desc, body);
	}
	
	public static SimpleResponse failOnlyDesc(String desc) {
		return new SimpleResponse(RspStatus.FAIL, desc, new HashMap<>());
	}
	
	public SimpleResponse(RspStatus status, String desc, Object body) {
		this.status = status;
		this.desc = desc;
		this.body = body;
	}
	
	public SimpleResponse(RspStatus status) {
		this.status = status;
	}

	public RspStatus getStatus() {
		return status;
	}

	public void setStatus(RspStatus status) {
		this.status = status;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public long getTimestamp() {
		return timestamp;
	}
	
	@Override
	public String toString() {
		Map<String, Object> temp = new HashMap<String, Object>();
		temp.put("status", this.status.name());
		temp.put("desc", this.desc);
		temp.put("body", body.toString());
		return temp.toString();
	}
	
	enum RspStatus {
		SUCCESS, FAIL;
	}
}
