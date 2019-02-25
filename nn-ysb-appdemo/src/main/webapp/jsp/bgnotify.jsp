<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@page import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		/**
		 *
		 *处理支付组件支付成功后，后台通知数据，
		 *当成功收到后告诉支付组件成功收到
		 *设置response.setHeader("NOTIFY_RESULT", "SUCCESS")
		 *
		 respCode :00 成功
		 respCode :02 支付中
		 respCode :01 支付失败
		 respCode :03 支付成功但失败
		 *
		 */
		response.setHeader("NOTIFY_RESULT", "SUCCESS");
		StringBuffer sb0 = new StringBuffer(" 支付后台通知参数:");
		Map map = new HashMap();
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			map.put(paraName, request.getParameter(paraName));
			sb0.append("\n");
			sb0.append(paraName + ": " + request.getParameter(paraName));
		}

		StringBuffer sb = new StringBuffer("");
		//验证返回结果
		if (map != null && map.get("orderStatus").toString().equals("3")) {
			sb = new StringBuffer("充值成功！");
			sb.append("\n");
			sb.append("充值卡充值平台后台通知返回参数:" + sb0);
			sb.append("\n");
			//签证返回数据是否合法
		 
		}else if (map != null && map.get("orderStatus").toString().equals("4")) {
			sb = new StringBuffer("充值失败！");
			sb.append("\n");
			sb.append("充值卡充值平台后台通知返回参数:" + sb0);
			sb.append("\n");
			//签证返回数据是否合法
		}else if (map != null && map.get("orderStatus").toString().equals("5")) {
			sb = new StringBuffer("充值异常！");
			sb.append("\n");
			sb.append("充值卡充值平台后台通知返回参数:" + sb0);
			sb.append("\n");
			//签证返回数据是否合法
		}else {
			sb = new StringBuffer("未知！");
			sb.append("\n");
			sb.append("充值卡充值平台后台通知返回参数:" + sb0);
			sb.append("\n");
			//签证返回数据是否合法
		 
		}
		System.out.println(sb.toString());
		
		
		// 写验签结果到 checkSign.txt
		String projectRealPath = request.getSession().getServletContext().getRealPath("/");
		String checkSignFilePath = projectRealPath + "/jsp/data/checkSign.txt";
		File checkSignFile = new File(checkSignFilePath);
		FileWriter writer = null;  
        try {     
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件     
            writer = new FileWriter(checkSignFile, true);     
            writer.write(sb.toString());
            writer.write(13);
            writer.write(10);
            writer.write("*******************************************************************");
            writer.write(13);
            writer.write(10);
        } catch (IOException e) {     
            e.printStackTrace();     
        } finally {     
            try {     
                if(writer != null){  
                    writer.close();     
                }  
            } catch (IOException e) {     
                e.printStackTrace();     
            }     
        }   
		
		
	%>

</body>
</html>