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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * 签名验签工具类.
 * <p>
 * 
 * @author kehy
 */
public class SignClassTool {

    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;

    public SignClassTool(String keydir) {
	File privateFile = new File(keydir + "private.key");
	File publicFile = new File(keydir + "public.key");
	FileInputStream fin = null;
	ObjectInputStream priin = null;
	try {
	    fin = new FileInputStream(privateFile);
	    priin = new ObjectInputStream(fin);
	    privateKey = (RSAPrivateKey) priin.readObject();
	    fin = new FileInputStream(publicFile);
	    priin = new ObjectInputStream(fin);
	    publicKey = (RSAPublicKey) priin.readObject();
	} catch (FileNotFoundException e) {
	    throw new SignException();
	} catch (IOException e) {
	    throw new SignException();
	} catch (ClassNotFoundException e) {
	    throw new SignException();
	} finally {
	    if (priin != null) {
		try {
		    priin.close();
		} catch (IOException e) {
		}
	    }
	    if (fin != null) {
		try {
		    fin.close();
		} catch (IOException e) {
		}
	    }
	}
    }

    public String sign(String str) {
	String rlt = null;
	try {
	    Signature rsa = Signature.getInstance("MD5withRSA");
	    rsa.initSign(privateKey);
	    byte[] obj = str.getBytes("GBK");
	    rsa.update(obj);
	    byte[] signData = rsa.sign();
	    rlt = Helper.bytesToString(signData);
	} catch (NoSuchAlgorithmException e) {
	    throw new SignException();
	} catch (InvalidKeyException e) {
	    throw new SignException();
	} catch (UnsupportedEncodingException e) {
	    throw new SignException();
	} catch (SignatureException e) {
	    throw new SignException();
	}
	return rlt;
    }

    public boolean verify(String str,String sign) {
	try {
	    Signature rsa = Signature.getInstance("MD5withRSA");
	    rsa.initVerify(publicKey);
	    byte[] obj = str.getBytes("GBK");
	    rsa.update(obj);
	    return rsa.verify(Helper.hexToBytes(sign));
	} catch (NoSuchAlgorithmException e) {
	    throw new SignException();
	} catch (InvalidKeyException e) {
	    throw new SignException();
	} catch (UnsupportedEncodingException e) {
	    throw new SignException();
	} catch (SignatureException e) {
	    throw new SignException();
	}
    }
    
}
