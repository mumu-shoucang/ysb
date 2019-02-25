<%@page import="java.io.*"%>
<%@page import="java.net.*"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	<%
	try {
		
		InetAddress.getByName("sms-cly.cn").getHostName();
		out.write(InetAddress.getByName("ccdcapi.alipay.com").getHostAddress());
		out.write(InetAddress.getByName("skb.yeepay.com").getHostAddress());
		
	} catch (Throwable thr) {
		thr.printStackTrace(new PrintWriter(out));
	}
	%>
