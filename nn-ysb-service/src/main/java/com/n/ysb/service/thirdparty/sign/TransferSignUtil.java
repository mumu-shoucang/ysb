package com.n.ysb.service.thirdparty.sign;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.n.ysb.service.thirdparty.vo.TransferVo;

public class TransferSignUtil {

private static final Logger logger = LoggerFactory.getLogger(TransferSignUtil.class);
    
    public static NameValuePair[] transfer(TransferVo par, String mainCustomerNumber, String key) {
    	
    		if(StringUtils.isBlank(mainCustomerNumber) || StringUtils.isBlank(key) || StringUtils.isBlank(par.getCustomerNumber()) || StringUtils.isBlank(par.getRequestId()) || StringUtils.isBlank(par.getTransAmount())) {
    			throw new IllegalArgumentException(" 转账接口存在空参数！！！ ");
    		}
    	
    		// mainCustomerNumber customerNumber requestId 	transAmount 	remark
        StringBuffer signBuffer = new StringBuffer();
        signBuffer.append(mainCustomerNumber)
                  .append(par.getCustomerNumber())
                  .append(par.getRequestId())
                  .append(par.getTransAmount())
                  .append(par.getRemark() == null ? "" : par.getRemark());
        
        String hmac = Digest.hmacSign(signBuffer.toString(), key);   
        logger.info("{}-易宝转账接口请求信息为：{}", par.getRequestId(), signBuffer);
        
        NameValuePair[] param = {
                // 系统商商户编号
                new NameValuePair("mainCustomerNumber", mainCustomerNumber),
                // 子商户编号
                new NameValuePair("customerNumber", par.getCustomerNumber()),
                // 转账订单号
                new NameValuePair("requestId", par.getRequestId()),
                // 转账金额
                new NameValuePair("transAmount", par.getTransAmount()),
                // 出款方式
                new NameValuePair("remark", par.getRemark()),
                // 备注
                new NameValuePair("hmac", hmac),

        };
        
        return param;
    }
}
