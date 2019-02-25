package com.n.ysb.service.business.core;

import org.apache.commons.lang.StringUtils;

public class NoFormat {

	public static String formatIdcard(String idcard) {
		if(StringUtils.isBlank(idcard))
			return "";
		
		String pattern = "****************";
		return idcard.charAt(0) + pattern + idcard.charAt(idcard.length()-1);
	}
	
	public static String formatBankNo(String bankNo) {
		if(StringUtils.isBlank(bankNo))
			return "";
		
		String pattern = "**** **** **** ";
		return pattern + bankNo.substring(bankNo.length()-4);
	}
	
	public static void main(String[] args) {
		System.out.println(formatIdcard("131127198701100568"));
		System.out.println(formatBankNo("6212260200008079473"));
	}
}
