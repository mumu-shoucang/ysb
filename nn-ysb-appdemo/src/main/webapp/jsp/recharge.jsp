<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat,java.util.Calendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

<% 
String time1="";
String time2="";

Calendar c = Calendar.getInstance();
SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
time1=formatter.format(c.getTime());


c.add(Calendar.MINUTE, 30);
time2=formatter.format(c.getTime());

%>	

<script type="text/javascript">
	//订单号随机数
	function randNum() {
		return <%=time1 %>;
		
	}
	
	$(function() {
		//用户id
		$("#meriOrderNo").val(randNum());
	});
</script>
</head>
<body>
	<form action="toRecharge.jsp" method="post" id="appform"
		name="appform" enctype="application/x-www-form-urlencoded">
		<table width="98%" border="0" align="center" cellpadding="0"
			cellspacing="1" class="table_xz2">
			 <tr>
				<td style="background-color: #DDDDDD; width: 150px;">请求测试地址:</td>
				<td><input type="text" name="url" value="http://192.168.99.100:8080/nn-fcr-http-web/orderRecharge/addOrderAndRecharge"  style="width: 800px;" class="{requiredBy:{src:'#bywrite',srccheck:'check'}}" /><font color="red">*</font></td>
			</tr>
			<tr>
				<td style="background-color: #DDDDDD; width: 150px;">
				跃程订单号：</td>
				<td><input id="meriOrderNo" name="meriOrderNo"
					 value="18012410582966"
					style="width: 300px;" type="text" maxlength="60" /><font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td style="background-color: #DDDDDD; width: 150px;">
				中石化加油卡卡号：</td>
				<td><input id="fuelcardNo" name="fuelcardNo"
					 value="1000110000111100000"
					style="width: 300px;" type="text" maxlength="60" /><font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td style="background-color: #DDDDDD; width: 150px;">
				充值面值：</td>
				<td>
					<select name="faceValue">
						<option value="200">200</option>
						<option value="500">500</option>
						<option value="1000">1000</option>
						<option value="2000">2000</option>
					</select>
					<font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td style="background-color: #DDDDDD; width: 150px;">
				回调地址：</td>
				<td><input id="bgNotifyUrl" name="bgNotifyUrl"
					 value="http://192.168.0.25:9082/nn-fcr-appdemo/jsp/bgnotify.jsp"
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