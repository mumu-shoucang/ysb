<%@page import="java.net.URLEncoder"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.n.recharge.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
        request.setCharacterEncoding("UTF-8"); 
        String url = request.getParameter("url");
        String merchantMobile = request.getParameter("merchantMobile");
        String pwd = request.getParameter("pwd");
		
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String ss = format.format(date);
        
        
		Map map = new HashMap();
		map.put("timestamp", ss);
		map.put("url", url);
		//map.put("merchantMobile", URLEncoder.encode(AESCipher.aesEncryptString(merchantMobile, "30TWH7TG72QBMXSA"), "UTF-8"));
		//map.put("pwd", URLEncoder.encode(AESCipher.aesEncryptString(pwd, "30TWH7TG72QBMXSA"), "UTF-8"));
		
		map.put("merchantMobile", AESUtil.encrypt(merchantMobile, "30TWH7TG72QBMXSA"));
		map.put("pwd", AESUtil.encrypt(pwd, "30TWH7TG72QBMXSA"));
		
		
		
		//map.put("sign", MD5.md5(ss + merchantMobile + URLEncoder.encode(AESCipher.aesEncryptString(pwd, "30TWH7TG72QBMXSA"), "UTF-8") + "659040EB004EAB3795D392A976A5E864"));
		map.put("sign", MD5.md5(ss + merchantMobile + AESUtil.encrypt(pwd, "30TWH7TG72QBMXSA") + "659040EB004EAB3795D392A976A5E864"));
		
		String returnMsg = HttpSend.send(map);
		
		System.out.println(returnMsg);
		
		out.write(returnMsg);
%>

