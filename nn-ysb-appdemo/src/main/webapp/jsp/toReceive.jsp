<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.n.recharge.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
        //临时参数不参与支付
        request.setCharacterEncoding("UTF-8"); 
        String url = request.getParameter("url");
        String merchantMobile = request.getParameter("merchantMobile");
        String creditCardCode = request.getParameter("creditCardCode");
        String orderAmt = request.getParameter("orderAmt");
        String token = request.getParameter("token");
        String mcc = request.getParameter("mcc");
		
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String ss = format.format(date);
        
		Map map = new HashMap();
		map.put("timestamp", ss);
		map.put("url", url);
		map.put("token", token);
		map.put("merchantMobile", AESUtil.encrypt(merchantMobile, "30TWH7TG72QBMXSA"));
		map.put("creditCardCode", creditCardCode);
		map.put("orderAmt", orderAmt);
		map.put("mcc", mcc);
		
		map.put("sign", MD5.md5(ss + merchantMobile + creditCardCode + orderAmt + "659040EB004EAB3795D392A976A5E864"));
		
		String returnMsg = ReceiveOrderTest.send(map);
		
		System.out.println(returnMsg);
		
		out.write(returnMsg);
		%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>去支付</title>
<script type="text/javascript">
</script>
</head>
<body>


</body>
</html>
