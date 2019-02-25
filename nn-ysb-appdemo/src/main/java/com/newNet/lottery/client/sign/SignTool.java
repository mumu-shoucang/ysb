package com.newNet.lottery.client.sign;

public interface SignTool {
    /**
     * 用私钥签名
     * @param str
     * @return
     */
    public abstract String sign(String str);
 
    /**
     * 用公钥验签
     * @param str
     * @param sign
     * @return
     */
    public abstract boolean verify(String str, String sign);

}