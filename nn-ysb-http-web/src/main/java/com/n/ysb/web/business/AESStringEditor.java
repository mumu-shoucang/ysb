package com.n.ysb.web.business;

import java.beans.PropertyEditorSupport;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.n.ysb.service.util.AESCipher;
import com.n.ysb.service.util.AESUtil;

public class AESStringEditor extends PropertyEditorSupport {
	
	private final static Logger log = LoggerFactory.getLogger(AESStringEditor.class);
	
	private String aesKey;
	
	private String header;
	
	public AESStringEditor(String key, String header) {
		this.aesKey = key;
		this.header = header;
	}
	
	/**
	 * Parse the Date from the given text, using the specified DateFormat.
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
	    log.info("解密前字符串为：{}", text);
	    
	    log.info("header为：{}", header);
	    
		if (!StringUtils.hasText(text)) {
			// Treat empty String as null value.
			setValue(null);
		} else {
		    if(header.contains("Android")){
		        setValue(AESUtil.decrypt(text, aesKey));
		    }else if(header.contains("iPhone") || header.contains("iPod") || header.contains("iPad")){
		        text = urlDecode(text);
		        setValue(AESCipher.aesDecryptString(text, aesKey));
		    }else{
		        //Android
		        setValue(AESUtil.decrypt(text, aesKey));
		        
		        //ios
//		        text = urlDecode(text);
//		        setValue(AESCipher.aesDecryptString(text, aesKey));
		        
		    }
		}
	}

	/**
	 * Format the Date as String, using the specified DateFormat.
	 */
	@Override
	public String getAsText() {
		return (String) getValue();
	}
	
	private String urlDecode(String text){
	    try {
            String decodedText = URLDecoder.decode(text, "UTF-8");
            return decodedText;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
	    log.info("手机号码{} decode Exception", text);
	    return "";
	}
}
