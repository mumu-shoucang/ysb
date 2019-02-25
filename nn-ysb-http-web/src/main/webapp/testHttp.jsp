<%@page import="org.apache.commons.httpclient.methods.PostMethod"%>
<%@page import="org.apache.commons.httpclient.methods.GetMethod"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.apache.commons.httpclient.*"%>
<%@page import="org.apache.commons.httpclient.params.HttpMethodParams"%>
<%@page import="java.lang.Exception"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Test HttpClient</title>
</head>
<body>
	<%
	try {
		HttpClient httpClient = new HttpClient();
		
		// 修改默认设置
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		httpClient.getHttpConnectionManager().getParams().setDefaultMaxConnectionsPerHost(16);
		httpClient.getHttpConnectionManager().getParams().setMaxTotalConnections(64);
		
		String url = "http://192.168.10.220:8080/nn-fcr-http-web/orderRecharge/orderQuery?meriOrderNo=20180530194708";
		PostMethod getMethod = new PostMethod(url);
		
				
			int code = httpClient.executeMethod(getMethod);
			out.write(" 发送支付通知，接入应用返回code为：" + code);
			out.write(" 地址为：" + url);
			out.write(" 返回信息为：" + getMethod.getResponseBodyAsString());
		
	} catch (Throwable thr) {
		thr.printStackTrace(new PrintWriter(out));
	}
	%>
</body>
</html>