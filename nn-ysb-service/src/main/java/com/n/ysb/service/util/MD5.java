package com.n.ysb.service.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5 {

	public static String md5(String text) {
		return DigestUtils.md5Hex(text);
	}
	
}
