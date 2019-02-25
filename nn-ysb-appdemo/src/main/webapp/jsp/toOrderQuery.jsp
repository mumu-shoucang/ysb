<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.n.recharge.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
        //临时参数不参与支付
        request.setCharacterEncoding("UTF-8"); 
        String url = request.getParameter("url");
		String merchantMobile = request.getParameter("merchantMobile");
		String token = request.getParameter("token");
		String pageNo = request.getParameter("pageNo");
		String limit = request.getParameter("limit");
		
		Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String ss = format.format(date);
        
        
		Map map = new HashMap();
		map.put("timestamp", ss);
		map.put("url", url);
		map.put("merchantMobile", AESUtil.encrypt(merchantMobile, "C4ACE3FDF696280DC17925B5F580CEC5"));
		map.put("pageNo", pageNo);
		map.put("limit", limit);
		map.put("token", "84eb10276de60ddc7b83749353bb44d3");
		
		map.put("sign", MD5.md5(ss + merchantMobile + "6CA6E127FBAB69AD8B86E2A12C2C9A64"));
		
		String returnMsg = HttpSend.send(map);
		
		System.out.println(returnMsg);
		
		out.write(returnMsg);

		%>



