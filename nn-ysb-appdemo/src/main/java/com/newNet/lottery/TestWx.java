package com.newNet.lottery;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;


public class TestWx {

    public static void main(String[] args) throws Exception {
//        HttpClient client = new HttpClient();
//        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
//        PostMethod postMethod = new PostMethod(url);
//        
//        String currTime = getCurrTime();
//        //8位日期
//        String strTime = currTime.substring(8, currTime.length());
//        //四位随机数
//        String strRandom = buildRandom(4) + "";
//        //随机字符串
//        String nonceStr = strTime + strRandom;
//        
//        SortedMap<String, String> prepayIdMap = new TreeMap<String, String>();
//        prepayIdMap.put("appid", "wx96eaa45a5a703b6a");  
//        prepayIdMap.put("mch_id", "1494012372");  
//        prepayIdMap.put("nonce_str", nonceStr);  
//        prepayIdMap.put("body", "微信支付测试");
//        prepayIdMap.put("out_trade_no", "chwx001001001");
//        prepayIdMap.put("total_fee", "1");
//        prepayIdMap.put("spbill_create_ip", "192.168.10.220");
//        prepayIdMap.put("notify_url", "https://www.baidu.com");
//        prepayIdMap.put("trade_type", "APP");
//        
//        String sign = createSign(prepayIdMap, "Hlxc2858ao3MVB9lqTgrKYjinbkl20hz");
//        
//        SortedMap<Object,Object> signMap = new TreeMap<Object, Object>();
//        signMap.putAll(prepayIdMap);
//        signMap.put("sign", sign);
//        
//        String requestXml = getRequestXml(signMap);
//        
//        postMethod.setRequestEntity(new StringRequestEntity(requestXml, "text/xml", "UTF-8"));
//        
//        String result = "";
//        BufferedInputStream bis = null;
//        ByteArrayOutputStream bos = null;
//        
//        int statusCode = client.executeMethod(postMethod);
//        System.out.println(statusCode);
//        
//        bis = new BufferedInputStream(postMethod.getResponseBodyAsStream());
//        byte[] bytes = new byte[1024];
//        bos = new ByteArrayOutputStream();
//        int count = 0;
//        while ((count = bis.read(bytes)) != -1) {
//            bos.write(bytes, 0, count);
//        }
//        byte[] strByte = bos.toByteArray();
//        result = new String(strByte, 0, strByte.length, "UTF-8");
//        bos.close();
//        bis.close();
//        System.out.println(result);
        
        URL url = new URL("https://wwww.baidu.com");
        System.out.println(url.getProtocol());
    }
    
    public static String getCurrTime() {
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = outFormat.format(now);
        return s;
    }
    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }
    public static String createSign(SortedMap<String, String> packageParams, String key) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k)
                    && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
        System.out.println("md5 sb:" + sb+"key="+key);
        String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8")
                .toUpperCase();
        System.out.println("packgeǩ��:" + sign);
        return sign;

    }
    public static String getRequestXml(SortedMap<Object, Object> map) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set<Entry<Object, Object>> es = map.entrySet();
        Iterator<Entry<Object, Object>> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) it
                    .next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k)
                    || "sign".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }
    
    
}
