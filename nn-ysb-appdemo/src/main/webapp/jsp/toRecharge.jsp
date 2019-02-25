<%@page import="java.util.*"%>
<%@page import="com.n.recharge.OrderRechargeSend"%>
<%@page import="com.newNet.lottery.MD5"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
        //临时参数不参与支付
        request.setCharacterEncoding("UTF-8"); 
        String url = request.getParameter("url");
		String meriOrderNo = request.getParameter("meriOrderNo");
		String fuelcardNo = request.getParameter("fuelcardNo");
		String faceValue = request.getParameter("faceValue");
		String bgNotifyUrl = request.getParameter("bgNotifyUrl");
		
		String sign = MD5.md5(meriOrderNo+fuelcardNo+faceValue+"0adbbf5f3c68699e04a03869b40b1c8d");

		Map map = new HashMap();
		map.put("url", url);
		map.put("meriOrderNo", meriOrderNo);
		map.put("fuelcardNo", fuelcardNo);
		map.put("faceValue", faceValue);
		map.put("bgNotifyUrl", bgNotifyUrl);
		map.put("sign", sign);

        
		
		String returnMsg = OrderRechargeSend.send(map);
		
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
