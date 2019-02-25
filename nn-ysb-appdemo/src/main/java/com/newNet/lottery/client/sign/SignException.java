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
 * 签名异常.
 * <p>
 *
 * @author kehy
 */
public class SignException extends RuntimeException {
    
    private int code;

    public SignException() {
	super();
    }

    public SignException(String message, Throwable cause) {
	super(message, cause);
    }

    public SignException(String message) {
	super(message);
    }

    public SignException(Throwable cause) {
	super(cause);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
