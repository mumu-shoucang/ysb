package com.n.ysb.service.thirdparty.sms;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Hex;
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

@Service
public class SMSImpl implements SMS {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Value("${sms.yt_url}")
    private String mtUrl;
    @Value("${sms.yt_spid}")
    private String spId;
    @Value("${sms.yt_sppassword}")
    private String spPwd;
    
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
        String command = "MT_REQUEST";
        String spsc = "00";
        String da = "86" + mobile;
        int dc = 15;
        String ecodeform = "GBK";
        String sm;
        try {
            sm = new String(Hex.encodeHex(msg.getBytes(ecodeform)));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException"+e);
        }

        StringBuilder smsUrl = new StringBuilder();
        smsUrl.append(mtUrl);
        smsUrl.append("?command=" + command);
        smsUrl.append("&spid=" + spId);
        smsUrl.append("&sppassword=" + spPwd);
        smsUrl.append("&spsc=" + spsc);
        smsUrl.append("&da=" + da);
        smsUrl.append("&sm=" + sm);
        smsUrl.append("&dc=" + dc);

        String resStr = null;
        GetMethod getMethod = new GetMethod(smsUrl.toString());
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

}
