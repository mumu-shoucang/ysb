<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat,java.util.Calendar"%>
<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/theme/style.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/sino.core.base-1.0.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/sino.core.tool-1.0.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.validate.sino-1.0.js"></script>

</head>
<body>
	<form action="toQueryBalance.jsp" method="post" id="appform"
		name="appform" enctype="application/x-www-form-urlencoded">
		<table width="98%" border="0" align="center" cellpadding="0"
			cellspacing="1" class="table_xz2">
			 <tr>
				<td style="background-color: #DDDDDD; width: 150px;">请求测试地址:</td>
				<td><input type="text" name="url" value="http://192.168.10.229:8080/nn-ysb-http-web/receive/getBalance"  style="width: 800px;" class="{requiredBy:{src:'#bywrite',srccheck:'check'}}" /><font color="red">*</font></td>
			</tr>
			<tr>
				<td style="background-color: #DDDDDD; width: 150px;">
				电话号码：</td>
				<td><input id="merchantMobile" name="merchantMobile"
					 value="13521314803"
					style="width: 300px;" type="text" maxlength="60" /><font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td style="background-color: #DDDDDD; width: 150px;">
				子商户编码：</td>
				<td><input id="yeeCustomerNumber" name="yeeCustomerNumber"
					 value="10023789149"
					style="width: 300px;" type="text" maxlength="60" /><font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td style="background-color: #DDDDDD; width: 150px;">
				token：</td>
				<td><input id="token" name="token"
					 value=""
					style="width: 300px;" type="text" maxlength="60" /><font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="">
					<input style="" name="button4" type="submit" id="button" value="确 定" />
				</td>
			</tr>
		</table>
	</form>
    
</body>
</html>