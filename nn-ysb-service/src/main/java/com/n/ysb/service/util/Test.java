package com.n.ysb.service.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Test {
    
    public static void main(String[] args) throws UnsupportedEncodingException {
//        String encrypt = AESUtil.encrypt("111111", "30TWH7TG72QBMXSA");
//        System.out.println(encrypt);
//        
//        String aesEncryptString = AESCipher.aesEncryptString("111111", "30TWH7TG72QBMXSA");
//        String encode = URLEncoder.encode(aesEncryptString, "UTF-8");
//        System.out.println(encode);
        
        
        String decrypt = AESUtil.decrypt("C5ADDE35609788F33BE3773E9A696BAB", "30TWH7TG72QBMXSA");
        
        String aesDecryptString = AESCipher.aesDecryptString(URLDecoder.decode("lHGZV%2B59FfgVGa722c5sfw%3D%3D", "UTF-8"), "30TWH7TG72QBMXSA");
        
        System.out.println(aesDecryptString);
        
    }

}
