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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;

/**
 * 帮助类.
 * <p>
 *
 * @author kehy
 */
public class Helper {

    public static String bytesToString(byte[] encrytpByte) {
	StringBuffer result = new StringBuffer();
	for (byte bytes : encrytpByte) {
	    result.append(byteHEX(bytes));
	}
	return result.toString();
    }

    private static String byteHEX(byte ib) {
	char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
		'B', 'C', 'D', 'E', 'F' };
	char[] ob = new char[2];
	ob[0] = digit[(ib >>> 4) & 0X0F];
	ob[1] = digit[ib & 0X0F];
	String s = new String(ob);
	return s;
    }
    
    public static byte[] hexToBytes(String hexString) {
	if (hexString == null || hexString.equals("")) {
	    return null;
	}
	hexString = hexString.toUpperCase();
	int length = hexString.length() / 2;
	char[] hexChars = hexString.toCharArray();
	byte[] d = new byte[length];
	for (int i = 0; i < length; i++) {
	    int pos = i * 2;
	    d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
	}
	return d;
    }

    private static byte charToByte(char c) {
	return (byte) "0123456789ABCDEF".indexOf(c);
    }
    
    public static URL getConfURL(String configFile,Class clazz) {
	URL url = null;
	ClassLoader threadContextClassLoader = Thread.currentThread()
		.getContextClassLoader();
	if (threadContextClassLoader != null) {
	    url = threadContextClassLoader.getResource(configFile);
	}
	if (url == null) {
	    url = clazz.getResource(configFile);
	    if (url == null) {
		throw new SignException("密钥配置文件读取异常");
	    }
	}
	return url;
    }
    
    /**
     * 将字节数组转换成字符串
     * @param keyByte
     * @return
     */
    public static String getKeyStr(byte[] keyByte){
        	String keyStr="";        	    
        	keyStr=new String(Base64.encodeBase64(keyByte)) ;
 	return keyStr;
    }
    
    /**
     * 将字符串转换成字节数组
     * @param keyByte
     * @return
     */
    public static byte[] getKeyByte(String keyStr){             	    
         byte [] base64=keyStr.getBytes(); 
         byte [] keyByte=Base64.decodeBase64(base64);      
 	return keyByte;
    }
    
    /**
     * 读取文件中的签名串
     * @throws Exception 
     */
    public String readKeyFile(File file) throws Exception { 
	if(file==null) {
	    throw new Exception("密钥文件为null");
	}
	FileInputStream fin = null;
	BufferedReader br = null;
	String keystr="";
	
	try {
	    String data = ""; 
	    fin = new FileInputStream(file);	    
	    br = new BufferedReader(new InputStreamReader(fin));  
	    while((data = br.readLine()) != null) { 
		keystr+=data; 
	    }  	    
	} catch (FileNotFoundException e) {
	    throw new SignException();
	} catch (IOException e) {
	    throw new SignException();
	} finally {
	    if (br != null) {
		try {
		    br.close();
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
	return keystr;
    }
}
