<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.n.recharge.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
        request.setCharacterEncoding("UTF-8"); 
        String url = request.getParameter("url");
        String merchantMobile = request.getParameter("merchantMobile");
        String cardNo = request.getParameter("cardNo");
        String token = request.getParameter("token");
        String bindMobile = request.getParameter("bindMobile");
        String smsCode = request.getParameter("smsCode");
        
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String ss = format.format(date);
        
		Map map = new HashMap();
		map.put("url", url);
		map.put("timestamp", ss);
		map.put("token", token);
		map.put("merchantMobile", AESUtil.encrypt(merchantMobile, "30TWH7TG72QBMXSA"));
		map.put("cardNo", cardNo);
		map.put("bindMobile", bindMobile);
		map.put("smsCode", smsCode);
		
		map.put("sign", MD5.md5(ss + merchantMobile + cardNo + "659040EB004EAB3795D392A976A5E864"));
		
		String returnMsg = HttpSend.send(map);
		
		System.out.println(returnMsg);
		
		out.write(returnMsg);
%>


