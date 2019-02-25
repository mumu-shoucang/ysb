package com.n.ysb.service.thirdparty.sign;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BalanceQuerySignUtil {

private static final Logger logger = LoggerFactory.getLogger(BalanceQuerySignUtil.class);
    
    public static NameValuePair[] buildPars(String customerNumber, String balanceType, String mainCustomerNumber, String key) {
    	
    		if(StringUtils.isBlank(mainCustomerNumber) || StringUtils.isBlank(key) || StringUtils.isBlank(customerNumber) || StringUtils.isBlank(balanceType)) {
    			throw new IllegalArgumentException(" 余额查询接口存在空参数！！！ ");
    		}
    	
    		// mainCustomerNumber customerNumber requestId 	transAmount 	remark
        StringBuffer signBuffer = new StringBuffer();
        signBuffer.append(mainCustomerNumber)
                  .append(customerNumber)
                  .append(balanceType);
        
        String hmac = Digest.hmacSign(signBuffer.toString(), key);   
        logger.info("{}-易宝余额查询接口请求信息为：{}", customerNumber, signBuffer);
        
        NameValuePair[] param = {
                // 系统商商户编号
                new NameValuePair("mainCustomerNumber", mainCustomerNumber),
                // 子商户编号
                new NameValuePair("customerNumber", customerNumber),
                // 可用余额类型
                new NameValuePair("balanceType", balanceType),
                new NameValuePair("hmac", hmac),

        };
        
        return param;
    }
}
