<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.n.recharge.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
        request.setCharacterEncoding("UTF-8"); 
        String url = request.getParameter("url");
        String merchantMobile = request.getParameter("merchantMobile");
        String refCode = request.getParameter("refCode");
        String pwd = request.getParameter("pwd");
        String smsCode = request.getParameter("smsCode");
		
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String ss = format.format(date);
        
        
		Map map = new HashMap();
		map.put("timestamp", ss);
		map.put("url", url);
		map.put("smsCode", smsCode);
		map.put("pwd", pwd);
		map.put("refCode", refCode);
		map.put("merchantMobile", AESUtil.encrypt(merchantMobile, "30TWH7TG72QBMXSA"));
		
		map.put("sign", MD5.md5(ss + merchantMobile + refCode + pwd + smsCode + "659040EB004EAB3795D392A976A5E864"));
		
		String returnMsg = HttpSend.send(map);
		
		System.out.println(returnMsg);
		
		out.write(returnMsg);
%>


