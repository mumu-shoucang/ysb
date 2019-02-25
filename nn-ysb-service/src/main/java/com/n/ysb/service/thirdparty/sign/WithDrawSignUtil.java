package com.n.ysb.service.thirdparty.sign;

import org.apache.commons.httpclient.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.n.ysb.service.merchant.vo.NnMerchantVo;
import com.n.ysb.service.thirdparty.TransferWay;
import com.n.ysb.service.thirdparty.vo.WithDrawVo;

public class WithDrawSignUtil {

private static final Logger logger = LoggerFactory.getLogger(WithDrawSignUtil.class);
    
    public static NameValuePair[] withDraw(NnMerchantVo merchantVo, WithDrawVo withDrawVo){
        StringBuffer signBuffer = new StringBuffer();
        signBuffer.append(withDrawVo.getWithDrawAmt() == null ? "" : withDrawVo.getWithDrawAmt())
                  .append(merchantVo.getYeeCustomerNumber() == null ? "" : merchantVo.getYeeCustomerNumber())
                  .append(withDrawVo.getExternalNo() == null ? "" : withDrawVo.getExternalNo())
                  .append(merchantVo.getMainCustomerNumber() == null ? "" : merchantVo.getMainCustomerNumber())
                  .append(TransferWay.T0.getCode())
                  .append(withDrawVo.getCallBackUrl() == null ? "" : withDrawVo.getCallBackUrl());
        
        logger.info("结算签名前拼接参数为：{}", signBuffer.toString()); 
        String hmac = Digest.hmacSign(signBuffer.toString(), merchantVo.getKey());   
        logger.info("结算签名为：{}", hmac);
        
        NameValuePair[] param = {
                // 出款金额
                new NameValuePair("amount", withDrawVo.getWithDrawAmt().toString()),
                // 子商户编号
                new NameValuePair("customerNumber", merchantVo.getYeeCustomerNumber()),
                // 出款订单号
                new NameValuePair("externalNo", withDrawVo.getExternalNo()),
                // 系统商户编号
                new NameValuePair("mainCustomerNumber", merchantVo.getMainCustomerNumber()),
                // 出款方式
                new NameValuePair("transferWay", TransferWay.T0.getCode()),
                // 结算方式
                new NameValuePair("callBackUrl",withDrawVo.getCallBackUrl()),
                // 签名串
                new NameValuePair("hmac", hmac),

        };
        
        return param;
    }
}
