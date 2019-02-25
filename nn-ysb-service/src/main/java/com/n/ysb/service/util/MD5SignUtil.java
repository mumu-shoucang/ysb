package com.n.ysb.service.util;

/**
 * Created by zjc on 2017/10/18.
 */

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;


/**
 * MD5SignUtil 加解密工具类
 */
public class MD5SignUtil {




    /**
     * BASE64 解密
     * @param key 需要解密的字符串
     * @return 字节数组
     * @throws Exception
     */
    public static byte[] decryptBase64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64 加密
     * @param key 需要加密的字节数组
     * @return 字符串
     * @throws Exception
     */
    public static String encryptBase64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static String sign(String text, String key, String input_charset) {
        text = text + key;
        System.out.println("签名字段"+text);
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }

    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static String signNew(String text, String key, String input_charset) {
        text = key + text + key;
//        System.out.println(text);
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }

    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param sign 签名结果
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static boolean verify(String sign,String text, String key, String input_charset) {
        text = text + key;
        String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
        if(mysign.equals(sign)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

    /**
     * 验签键值对
     * @param dataMap 键值对
     * @param encriptkey 加密key
     * @return
     */
    public static boolean verifySign(Map<String,String> dataMap, String encriptkey){
        Map<String,String> tMap=new TreeMap<String,String>(dataMap);
        StringBuffer buf = new StringBuffer();
        String inputSign=tMap.get("sign");
        for (String key : tMap.keySet()) {
            if("signType".equals(key)||"sign".equals(key)){
                continue;
            }
            if(StringUtils.isBlank(tMap.get(key))){
                continue;
            }
            buf.append(key).append("=").append((String) tMap.get(key)).append("&");
        }
        String signatureStr = buf.substring(0, buf.length() - 1);
        return MD5SignUtil.verify(inputSign,signatureStr,encriptkey,"utf-8");
    }

    /**
     * 签名键值对
     * @param dataMap 签名键值对
     * @param encriptkey 加密key
     * @return
     */
    public static String sign(Map<String,String> dataMap,String encriptkey){
        Map<String,String> tMap=new TreeMap<String,String>(dataMap);
        StringBuffer buf = new StringBuffer();
        for (String key : tMap.keySet()) {
            if("signType".equals(key)||"sign".equals(key)){
                continue;
            }
            if(StringUtils.isBlank(tMap.get(key))){
                continue;
            }
            buf.append(key).append("=").append((String) tMap.get(key)).append("&");
        }
        if(buf.length()<1){
            return "";
        }
        String signatureStr = buf.substring(0, buf.length() - 1);
        return MD5SignUtil.sign(signatureStr,encriptkey,"utf-8");
    }

    /**
     * 签名键值对
     * @param dataMap 签名键值对
     * @param encriptkey 加密key
     * @return
     */
    public static String signNew(Map<String,Object> dataMap,String encriptkey){
        Map<String,Object> tMap=new TreeMap(dataMap);
        StringBuffer buf = new StringBuffer();
        for (String key : tMap.keySet()) {
            if("sign".equals(key)){
                continue;
            }

            if (tMap.get(key) == null) {
                continue;
            }

            if(StringUtils.isBlank(tMap.get(key) + "")){
                continue;
            }
            buf.append(key).append(tMap.get(key));
        }
        if(buf.length()<1){
            return "";
        }
        String signatureStr = buf.substring(0, buf.length());
        return MD5SignUtil.signNew(signatureStr,encriptkey,"utf-8").toUpperCase();
    }

    /**
     * 签名键值对
     * @param dataMap 签名键值对
     * @param encriptkey 加密key
     * @return
     */
    public static String signKeyValueObjMap(Map<String,Object> dataMap,String encriptkey){
        Map<String,Object> tMap=new TreeMap(dataMap);
        StringBuffer buf = new StringBuffer();
        for (String key : tMap.keySet()) {
            if("signType".equals(key)||"sign".equals(key)){
                continue;
            }
            if(StringUtils.isBlank(tMap.get(key).toString())){
                continue;
            }
            buf.append(key).append("=").append(tMap.get(key)).append("&");
        }
        if(buf.length()<1){
            return "";
        }
        String signatureStr = buf.substring(0, buf.length() - 1);
        return MD5SignUtil.sign(signatureStr,encriptkey,"utf-8");
    }

    /**
     * 签名键值对
     * @param dataMap 签名键值对
     * @param encriptkey 加密key
     * @return
     */
    public static String signWithKey(Map<String,String> dataMap,String encriptkey){
        Map<String,String> tMap=new TreeMap<String,String>(dataMap);
        StringBuffer buf = new StringBuffer();
        for (String key : tMap.keySet()) {
            if(StringUtils.isBlank(tMap.get(key))){
                continue;
            }
            buf.append(key).append("=").append(tMap.get(key)).append("&");
        }
        buf.append("key=").append(encriptkey);
        if(buf.length()<1){
            return "";
        }
        String signatureStr = buf.substring(0, buf.length());
        System.out.println("签名字段："+ signatureStr);
        return DigestUtils.md5Hex(getContentBytes(signatureStr, "utf-8"));
    }

    /**
     * 签名键值对
     * @param dataMap 签名键值对
     * @param encriptkey 加密key
     * @return
     */
    public static String signYSB(Map<String,String> dataMap,String encriptkey){
        Map<String,String> tMap=new LinkedHashMap<>(dataMap);
        StringBuffer buf = new StringBuffer();
        for (String key : tMap.keySet()) {
            if(StringUtils.isBlank(tMap.get(key))){
                continue;
            }
            buf.append(key).append("=").append(tMap.get(key)).append("&");
        }
        buf.append("key=").append(encriptkey);
        if(buf.length()<1){
            return "";
        }
        String signatureStr = buf.substring(0, buf.length());
        System.out.println("签名字段："+ signatureStr);
        return DigestUtils.md5Hex(getContentBytes(signatureStr, "utf-8"));
    }

    public static void main(String[] args) {
//        String srt = DigestUtils.md5Hex(getContentBytes("amount=1000&bankNm=建设银行&corgId=TESTGROUP&credit=+AWe7b2isAd6WusUxRlJZYOq/gPfyaZIWRuvG2Yvd20=&cvn=Vs5hxtk2IdWeg/OZhT/hQQ==&dateTime=20180416143533&debit=IwXbVJ7f2/xPwwjNDcopdYTI++BYxoF2b+VYytJ0FBg=&expiry=t/IWVIChrOslHzaJ2D5dZA==&frontUrl=www.baidu.com&idno=Depwq2c5PE5MwzeR+k/XK3c8ImYg+xFOHa7X+oe7KlU=&mcc=4511&mobile=FQyT+p3PbohRhajimcojWA==&name=Z7tZ28s+d08qr5YfgvLDMQ==&notifyUrl=www&outOrderId=1523862393183abc&requestNo=1523862392917&rslMobile=FQyT+p3PbohRhajimcojWA==&service=QuickPayCj&sumerAmt=200&sumerFee=0.35&version=1.0oUZ0WVNemMg0M3FKe3qmb4MFsjmkIq8x", "utf-8"));
//        System.out.println(srt);
    	
//    	机构号：BeiJingYiChuang
//    	SignKey：szrLofsk6cseWHnJ45RHmLXdNxWustHq
//    	AESKey：zdbN72c4m72Bm71vxmlVXxf1xEMhbrMW

    		Map<String, String> dataMap = new HashMap<>();
    		dataMap.put("corgId", "BeiJingYiChuang");
    		String s = sign(dataMap, "szrLofsk6cseWHnJ45RHmLXdNxWustHq");
    		System.out.println(s); // e72a67108ca05e70e1056264fde2caa1
    		// http://www.covsen.com/bankaNew/index.html?corgId=BeiJingYiChuang&sign=e72a67108ca05e70e1056264fde2caa1
    			
    }
}