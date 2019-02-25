package com.n.ysb.web.base;


public class SimpleResponse {
	
	private RspStatus status;
	private Object body;
	
	private final long timestamp = System.currentTimeMillis();
	
	public static SimpleResponse suc(Object body) {
		return new SimpleResponse(RspStatus.SUCCESS, body);
	}
	
	public static SimpleResponse fail(Object body) {
		return new SimpleResponse(RspStatus.FAIL, body);
	}
	
	public SimpleResponse(RspStatus status, Object body) {
		this.status = status;
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

	public long getTimestamp() {
		return timestamp;
	}
	
	enum RspStatus {
		SUCCESS, FAIL;
	}
}
