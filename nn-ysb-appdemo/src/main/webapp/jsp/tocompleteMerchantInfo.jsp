<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.n.recharge.*"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="org.apache.commons.fileupload.disk.*"%>
<%@page import="org.apache.commons.fileupload.*"%>
<%@page import="org.apache.commons.fileupload.servlet.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
		// string pars
        request.setCharacterEncoding("UTF-8"); 
		
		String returnMsg = HttpSend.completeInfo(request);
		
		System.out.println(returnMsg);
		
		out.write(returnMsg);
		
%>
