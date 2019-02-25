package com.newNet.lottery.client.sign;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;

import com.newNet.lottery.client.sign.crypt.AESTool;
import com.newNet.lottery.client.sign.crypt.AESToolFactory;

public class KeyCore {
    /** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }

    /**
     * 获取待签名串
     * @param parameters
     * @return
     */
    public static String getSignStr(Map<String, String> parameters) {
        // 除去数组中的空值和签名参数
        Map<String, String> result = new HashMap<String, String>();
        for (String key : parameters.keySet()) {
            String value = (String) parameters.get(key);
            if ("null" == value  || value == null || value.equals("") || key.equalsIgnoreCase("sign")) {
                continue;
            }
            result.put(key, value);
        }
        // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串;
        return KeyCore.createLinkString(result);
    }

    /**
     * 
     * 获取签名串
     * 
     * @param signStr 待签名字符串
     * @return sign
     */
    public static String getSign(String signStr, String path) {
        // String dir = Helper.getConfURL("key/", KeyCore.class).getPath();
        KeyTool keyTool = new KeyTool(path);
        String sign = keyTool.sign(signStr);
        return sign;
    }

    public static String getSign(String signStr) {
        String dir = Helper.getConfURL("key/", KeyCore.class).getPath();
        KeyTool keyTool = new KeyTool(dir);
        String sign = keyTool.sign(signStr);
        return sign;
    }
    
    /**
     * 
     * 获取签名串
     * 
     * @param signStr 待签名字符串
     * @return sign
     */
    public static String getSignByAppCode(String signStr, String appCode) {
        String dir = Helper.getConfURL("key/"+appCode, KeyCore.class).getPath();
        KeyTool keyTool = new KeyTool(dir);
        String sign = keyTool.sign(signStr);
        return sign;
    }

/**
 * 
 * @param originalStr 原始加密串
 * @param path 密钥路径
 * @return 加密后内容
 */
    public static String getEncrypt(String originalStr, String path) {
        if(StringUtils.isBlank(originalStr)){
            return "";  
          }
        File aesKey = getAESKey( path);
        valCrypt(aesKey);
        return encrypt(originalStr, aesKey);
    }
    /**
     * 
     * @param originalStr 原始加密串
     * @return 加密后内容
     */
    public static String getEncrypt(String originalStr) {

        return getEncrypt( originalStr, null);

    }
    /**
     * 
     * @param path 
     * @return AesFile
     */
    private static File getAESKey(String path){
        String dir ;
        if(StringUtils.isBlank(path)){
             dir = Helper.getConfURL("key/", KeyCore.class).getPath();
        }else{
            dir = path;
        }
        return new File(dir + "aes.key");
    }
    /**
     * 
     * @param encryptStr
     * @param aesKey
     * @return
     */
    private static String encrypt(String originalStr, File aesKey) {
        String result = "";
        AESTool tool = AESToolFactory.getAESTool();
        try {
            return tool.encryptWithKey(originalStr, aesKey);
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        return result;
    }
    private static void valCrypt( File keyFile){
        if(!keyFile.exists()){
            throw new IllegalArgumentException("AES密钥不存在！");
        }
        
    }

    public static String getDecrypt(String cryptStr) {
        
       return getDecrypt(cryptStr,null);
    }

    public static String getDecrypt(String cryptStr,String path) {
        if(StringUtils.isBlank(cryptStr)){
            return "";  
          }
        File aesKey = getAESKey( path);
        valCrypt(aesKey);
        AESTool tool = AESToolFactory.getAESTool();
        
        try {
          return tool.decryptWithKey(cryptStr, aesKey);
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return  null;
       
    }

    /**
     * 
     * 验证签名数据是否合法
     * 
     * @param str
     *            待签名串
     * @param sign
     *            签名数据
     * @param path
     *            密钥路径
     * @return boolean
     */
    public static boolean valSign(String str, String sign, String path) {
        // String dir = Helper.getConfURL("key/", KeyCore.class).getPath();
        KeyTool keyTool = new KeyTool(path);
        boolean b = keyTool.verify(str, sign);
        return b;
    }
    /**
     * 
     * 验证签名数据是否合法
     * 
     * @param str
     *            待签名串
     * @param sign
     *            签名数据
     * @return boolean
     */
    public static boolean valSign(String str, String sign) {
        String dir = Helper.getConfURL("key/", KeyCore.class).getPath();
        KeyTool keyTool = new KeyTool(dir);
        boolean b = keyTool.verify(str, sign);
        return b;
    }

    /**
     * 除去数组中的空值和签名参数
     * 
     * @param sArray
     *            签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {
        Map<String, String> result = new HashMap<String, String>();
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }

    /**
     * 查询支付结果.
     * 
     * @param appCode
     *            接入应用号
     * @param orderNo
     *            消费单号
     * @param url
     *            查询URL
     * @return 响应字符串
     */
    public static String query(Map map) {
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
//        httpClient.getHttpConnectionManager().getParams().setSoTimeout(10000);
        PostMethod postMethod = new PostMethod(map.get("url").toString());
        
        
        
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(0, false));
        
//        try {
//            map.remove("url");
//            String createLinkString = createLinkString(map);
//            postMethod.setRequestEntity(new StringRequestEntity(createLinkString,"application/x-www-form-urlencoded; charset=utf-8","UTF-8"));
//            postMethod.addParameter("openId", "https://www.baidu.com?tradId=HLXC201710131338331655&param=undi");
//        } catch (UnsupportedEncodingException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
        
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
            throw new CallException("查询异常.", e);
        }
        return null;
    }
    
    /**
     * 查询签约结果.
     * 
     * @param url
     *            查询URL
     * @return 响应字符串
     */
    public static String querySignProtocol(Map map) {
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
        PostMethod postMethod = new PostMethod(map.get("url").toString());
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(0, false));
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String keyString = (String) it.next();
            if (!"url".equals(keyString)) {
                postMethod.addParameter(keyString, map.get(keyString).toString());
            }
        }
        try {
            int code = httpClient.executeMethod(postMethod);
            if (code == HttpStatus.SC_OK) {
                return postMethod.getResponseBodyAsString();
            }
        } catch (Exception e) {
            throw new CallException("签约查询异常.", e);
        }
        return null;
    }

    /**
     * 查询分期银行信息
     * 
     * @param appCode
     *            接入应用号
     * @param orderNo
     *            消费单号
     * @param url
     *            查询URL
     * @return 响应字符串
     */
    public static String fqQuery(Map map) {
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
        PostMethod postMethod = new PostMethod(map.get("url").toString());
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(0, false));
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String keyString = (String) it.next();
            if (!"url".equals(keyString)) {
                postMethod.addParameter(keyString, map.get(keyString).toString());
            }
        }
        try {
            int code = httpClient.executeMethod(postMethod);
            if (code == HttpStatus.SC_OK) {
                return postMethod.getResponseBodyAsString();
            }
        } catch (Exception e) {
            throw new CallException("查询异常.", e);
        }
        return null;
    }

    /**
     * @param map
     * @return
     */
    public static String royalty(Map map) {
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
        PostMethod postMethod = new PostMethod(map.get("royaltyurl").toString());
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(0, false));
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String keyString = (String) it.next();
            if (!"royaltyurl".equals(keyString)) {
                postMethod.addParameter(keyString, map.get(keyString).toString());
            }
        }
        try {
            int code = httpClient.executeMethod(postMethod);
            if (code == HttpStatus.SC_OK) {
                return postMethod.getResponseBodyAsString();
            }
        } catch (Exception e) {
            throw new CallException("支付宝分润异常.", e);
        }
        return null;
    }
public static void main(String[] args) {
  String a =   KeyCore.encrypt("77777", new File("E://aes.key"));
  System.out.println(a);
  String b = KeyCore.getDecrypt(a);
  System.out.println(b);
  
}
}
