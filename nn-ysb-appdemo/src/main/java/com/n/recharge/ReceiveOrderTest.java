package com.n.recharge;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;

import com.newNet.lottery.client.sign.CallException;

public class ReceiveOrderTest {

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
