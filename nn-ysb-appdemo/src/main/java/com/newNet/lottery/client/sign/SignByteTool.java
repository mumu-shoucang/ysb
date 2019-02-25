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

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 签名验签工具类.
 * <p>
 * 
 * @author kehy
 */
public class SignByteTool {

    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;

    public SignByteTool(String keydir) {
	File privateFile = new File(keydir + "private.key");
	File publicFile = new File(keydir + "public.key");
	FileInputStream fin = null;
	try {
	    fin = new FileInputStream(privateFile);
	    ByteArrayOutputStream bout = new ByteArrayOutputStream();
	    byte[] tmpbuf = new byte[1024];
	    int count = 0;
	    while ((count = fin.read(tmpbuf)) != -1) {
    	    	bout.write(tmpbuf, 0, count);
    	    	tmpbuf = new byte[1024];
	    }
	    fin.close();
	    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	    EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bout.toByteArray());
	    privateKey = (RSAPrivateKey)keyFactory.generatePrivate(privateKeySpec);
	    
	    
	    
	    fin = new FileInputStream(publicFile);
	    bout = new ByteArrayOutputStream();
	    tmpbuf = new byte[1024];
	    count = 0;
	    while ((count = fin.read(tmpbuf)) != -1) {
    	    	bout.write(tmpbuf, 0, count);
    	    	tmpbuf = new byte[1024];
	    }
	    fin.close();
	    EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(bout.toByteArray());
	    publicKey = (RSAPublicKey)keyFactory.generatePublic(publicKeySpec);
	} catch (FileNotFoundException e) {
	    throw new SignException();
	} catch (IOException e) {
	    throw new SignException();
	} catch (NoSuchAlgorithmException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (InvalidKeySpecException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
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
