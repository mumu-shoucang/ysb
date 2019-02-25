package com.n.recharge;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;

import com.newNet.lottery.client.sign.CallException;

public class HttpSend {
	
	public static String completeInfo(HttpServletRequest request) throws Exception {
		
        // pars
		DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 1024 * 1024);// 设置缓存大小
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));// 默认情况下 临时文件不会自动删除
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
        List<FileItem> fileItemFromRequestList = servletFileUpload.parseRequest(request);
        
        List<Part> parts = new ArrayList<Part>(26);
        
        Map<String, String> map = new HashMap<String, String>();
        for(FileItem item : fileItemFromRequestList) {
        	if(StringUtils.isBlank(item.getName()) && !item.getFieldName().contains("button")) {
        		map.put(item.getFieldName(), item.getString("UTF-8"));
        	}
        }
        
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String ss = format.format(date);
        String mobile = map.get("merchantMobile").toString();
		map.put("timestamp", ss);
		map.put("sign", MD5.md5(ss + mobile + map.get("bankAccountNo").toString() + map.get("idCard").toString() + "659040EB004EAB3795D392A976A5E864"));
		map.put("merchantMobile", AESUtil.encrypt(mobile, "C4ACE3FDF696280DC17925B5F580CEC5"));
		
		// string pars
		Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String keyString = (String) it.next();
            if (!"url".equals(keyString)) {
                if(StringUtils.isNotBlank(map.get(keyString).toString())){
                	parts.add(new StringPart(keyString, map.get(keyString).toString(), "UTF-8"));
                }
            }
        }
        
        // file pars
        String classpath = HttpSend.class.getClassLoader().getResource("/").getPath();
		String projectPath = classpath.substring(0, classpath.indexOf("WEB-INF"));
		String photoPath = projectPath + "upload" + File.separator + mobile + File.separator;
		
		if(!new File(photoPath).exists()) {
			new File(photoPath).mkdirs();
		}
		
		for(FileItem item : fileItemFromRequestList) {
			if(StringUtils.isNotBlank(item.getName()) && !item.getFieldName().contains("button")) {
				File file = new File(photoPath + item.getName());
				if(!file.exists()) {
					file.createNewFile();
				} 
				item.write(file);
	        	
				FilePart fp = new FilePart(item.getFieldName(), file);
				String fileName = file.getName();
				fp.setContentType("image/"+ fileName.substring(fileName.lastIndexOf('.') + 1));
				fp.setCharSet("UTF-8");
				parts.add(fp);
			}
		}
		
		// do http
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(map.get("url").toString());
        
        postMethod.setRequestEntity(new MultipartRequestEntity(parts.toArray(new Part[parts.size()]), postMethod.getParams()));
        
        try {
            int code = client.executeMethod(postMethod);
            
            if(code == HttpStatus.SC_OK){
                String responseString = postMethod.getResponseBodyAsString();
                return responseString;
            }else{
                return null;
            }
        } catch (HttpException e) {
            throw new RuntimeException("HttpException"+e);
        } catch (IOException e) {
            throw new RuntimeException("IOException"+e);
        } finally{
            postMethod.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
        }
	}
	
    public static String send(Map map) {
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
        PostMethod postMethod = new PostMethod(map.get("url").toString());
        
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(0, false));
        
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String keyString = (String) it.next();
            if (!"url".equals(keyString)) {
                if(StringUtils.isNotBlank(map.get(keyString).toString())){
                    postMethod.addParameter(keyString, map.get(keyString).toString());
                }
            }
        }
        
        try {
            int code = httpClient.executeMethod(postMethod);
            if (code == HttpStatus.SC_OK) {
                return postMethod.getResponseBodyAsString();
            }
        } catch (Exception e) {
            throw new CallException("订单发送异常.", e);
        }
        return null;
    }
}
