/* 
 * ********************************************************************
 * 
 * Web site:http://www.sinovatech.com.
 * 
 * Copyright 2011- Beijing Sinova Technologies,Inc.All Rights Reserved
 * 
 * Createdate : 2012
 * 
 * ********************************************************************
 */
package com.newNet.lottery.client.sign;

/**
 * 接口调用异常.
 * <p>
 *
 * @author kehy
 */
public class CallException extends RuntimeException {

    public CallException() {
	super();
    }

    public CallException(String message, Throwable cause) {
	super(message, cause);
    }

    public CallException(String message) {
	super(message);
    }

    public CallException(Throwable cause) {
	super(cause);
    }

}
