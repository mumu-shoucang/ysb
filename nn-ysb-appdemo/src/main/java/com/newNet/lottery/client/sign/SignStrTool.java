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

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;




/**
 * 签名验签工具类,通过读取加密串，解析公私钥进行加密解密
 * <p>
 * 
 * @author kehy
 */
public class SignStrTool implements SignTool {
    // 公钥
    private byte[] publicKey;    
    // 私钥
    private byte[] privateKey;
    // 非对称加密密钥算法
    private static final  String Algorithm="RSA";
    private static final  String SignAlgorithm="MD5withRSA";

    public SignStrTool(String keydir) {
	File privateFile = new File(keydir + "private.key");
	File publicFile = new File(keydir + "public.key");
	//读取密钥文件内容
	Helper helper=new Helper();
	String privateKeystr="";	 
	String publicKeystr="";
	try {
	    privateKeystr = helper.readKeyFile(privateFile);
	    publicKeystr = helper.readKeyFile(publicFile);
	} catch (Exception e) {
	    throw new SignException();
	}
	//将字符串转换成byte []	
	privateKey=Helper.getKeyByte(privateKeystr);
	publicKey=Helper.getKeyByte(publicKeystr);
    }
    /* (non-Javadoc)
     * @see com.sinovatech.payment.client.SignTool#verify(java.lang.String, java.lang.String)
     */
     
    
    public String sign(String str) { 
	String rlt = null;		 
	try {
	 // 得到私钥
	   PKCS8EncodedKeySpec keySpec=new PKCS8EncodedKeySpec(privateKey);
	   KeyFactory kf = KeyFactory.getInstance(Algorithm);	 
	   PrivateKey  keyPrivate = kf.generatePrivate(keySpec);
	   
	   Signature rsa = Signature.getInstance(SignAlgorithm);
	   rsa.initSign(keyPrivate);
	   byte[] obj = str.getBytes("GBK");
	   rsa.update(obj);
	   byte[] signData = rsa.sign();
	   rlt = Helper.bytesToString(signData);
	   
	} catch (NoSuchAlgorithmException e) {
	    throw new SignException("签名算法异常");
	} catch (InvalidKeyException e) {
	    throw new SignException("读密钥文件异常");
	} catch (UnsupportedEncodingException e) {
	    throw new SignException("不支持字符编码");
	} catch (SignatureException e) {
	    throw new SignException("签名异常");
	}catch (InvalidKeySpecException e) {
	    throw new SignException("无效密钥异常");
	} 
	return rlt;	
    }

    /* (non-Javadoc)
     * @see com.sinovatech.payment.client.SignTool#verify(java.lang.String, java.lang.String)
     */
    public boolean verify(String str,String sign) {
	try {
	    Signature rsa = Signature.getInstance(SignAlgorithm);	    
	    // 得到公钥
	    X509EncodedKeySpec keySpec=new X509EncodedKeySpec(publicKey);
	    KeyFactory kf=KeyFactory.getInstance(Algorithm);
	    PublicKey keyPublic = kf.generatePublic(keySpec);	    
	    rsa.initVerify(keyPublic);
	    byte[] obj = str.getBytes("GBK");
	    rsa.update(obj);
	    return rsa.verify(Helper.hexToBytes(sign));
	} catch (NoSuchAlgorithmException e) {
	    throw new SignException("签名算法异常");
	} catch (InvalidKeyException e) {
	    throw new SignException("读密钥文件异常");
	} catch (UnsupportedEncodingException e) {
	    throw new SignException("不支持字符编码");
	} catch (SignatureException e) {
	    throw new SignException("签名异常");
	}catch (InvalidKeySpecException e) {
	    throw new SignException("无效密钥异常");	 
	}	
  
    }
    
    
}
