<%@page import="java.io.PrintWriter"%>
<%@page import="org.apache.commons.httpclient.*"%>
<%@page import="org.apache.commons.httpclient.methods.PostMethod"%>
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
		
		String url = "http://p.2858.com/nn-payment-http-web/sys/appPay/consume.controller";
		PostMethod postMethod = new PostMethod(url);
		
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(0, false));
		
		postMethod.addParameter("respCode", "00");
        postMethod.addParameter("respDesc", "通知陈琳接口");
        postMethod.addParameter("appCode", "1234567");
        postMethod.addParameter("appOrderNo", "1234567");
        postMethod.addParameter("orderNo2", "1234567");
		int code = httpClient.executeMethod(postMethod);
		out.write(" 发送支付通知，接入应用返回code为：" + code);
		out.write(" 地址为：" + url);
	} catch (Throwable thr) {
		thr.printStackTrace(new PrintWriter(out));
	}

	%>

</body>
</html>