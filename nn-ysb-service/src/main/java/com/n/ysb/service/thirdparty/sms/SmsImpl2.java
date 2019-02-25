package com.n.ysb.service.thirdparty.sms;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.n.ysb.service.util.MD5;

@Service
public class SmsImpl2 implements SMS2 {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Value("${sms.url}")
    private String smsUrl;
    
    @Value("${sms.balancequery.url}")
    private String balanceQueryUrl;
    
    @Value("${sms.username}")
    private String username;
    
    @Value("${sms.password}")
    private String password;
    
    static HttpClient client;
    
    static{
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = connectionManager.getParams();
        
        params.setConnectionTimeout(30000);  
        params.setSoTimeout(30000);  
        params.setDefaultMaxConnectionsPerHost(32);  
        params.setMaxTotalConnections(256);  
        
        connectionManager.setParams(params);
        client = new HttpClient(connectionManager);
    }
    
    @Override
    public void sendSMSCode(String mobile, String smsCode) {
        String sendsingleMzt = this.sendSingleMt(mobile, smsCode);
        logger.info("{}发送短信返回{}", mobile, sendsingleMzt);
    }
    
    public String sendSingleMt(String mobile, String msg){
        String ecodeform = "UTF-8";
        String content;
        try {
            content = URLEncoder.encode(msg, ecodeform);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException"+e);
        }
        String smsPWd = MD5.md5(username+MD5.md5(password));

        StringBuilder smsStr = new StringBuilder();
        smsStr.append(smsUrl);
        smsStr.append("?username=" + username);
        smsStr.append("&password=" + smsPWd);
        smsStr.append("&mobile=" + mobile);
        smsStr.append("&content=" + content);

        String resStr = null;
        GetMethod getMethod = new GetMethod(smsStr.toString());
        try {
            int code = client.executeMethod(getMethod);
            if(code == HttpStatus.SC_OK){
                resStr = getMethod.getResponseBodyAsString();
            }
            return resStr;
        } catch (HttpException e) {
            throw new RuntimeException("HttpException"+e);
        } catch (IOException e) {
            throw new RuntimeException("IOException"+e);
        } finally{
            getMethod.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
        }
        
    }
    
    @Override
    public String balanceQuery(){
        String smsPWd = MD5.md5(username+MD5.md5(password));

        StringBuilder smsStr = new StringBuilder();
        smsStr.append(balanceQueryUrl);
        smsStr.append("?username=" + username);
        smsStr.append("&password=" + smsPWd);
        String resStr = null;
        GetMethod getMethod = new GetMethod(smsStr.toString());
        try {
            int code = client.executeMethod(getMethod);
            if(code == HttpStatus.SC_OK){
                resStr = getMethod.getResponseBodyAsString();
            }
            return resStr;
        } catch (HttpException e) {
            throw new RuntimeException("HttpException"+e);
        } catch (IOException e) {
            throw new RuntimeException("IOException"+e);
        } finally{
            getMethod.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
        }
    }
    
    public static void main(String[] args) {
//        List<String> ss = new ArrayList<String>();
//       for (int i = 1; i <= 10000; i++) {
//           String code = new Random().nextInt(1000000)+"";
//           if(code.length()!=6){
//               ss.add(code);
//           }
//       }
//       
//       System.out.println(ss.size());
//       System.out.println(ss);
//       System.out.println("============================================");
//       
//       for (String string : ss) {
//        if(string.length()!=5){
//            System.out.println(string);
//        }
//       }
        
        
        
      List<String> ss = new ArrayList<String>();
     for (int i = 1; i <= 100000; i++) {
         String code = buildSmsCode();
         if(code.length()!=6){
             ss.add(code);
         }
     }
     
     System.out.println(ss.size());
     System.out.println(ss);
     System.out.println("============================================");
     
        
      
    }
    
    private static String buildSmsCode(){
        String smsCode ="";
        while(true){
            smsCode = new Random().nextInt(1000000) + "";
            if(smsCode.length()==6){
                break;
            }
        }
        
        return smsCode;
    }
}
