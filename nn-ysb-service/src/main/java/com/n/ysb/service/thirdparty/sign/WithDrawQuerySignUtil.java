package com.n.ysb.service.thirdparty.sign;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.httpclient.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.n.ysb.service.merchant.vo.NnMerchantVo;
import com.n.ysb.service.thirdparty.vo.WithDrawVo;

public class WithDrawQuerySignUtil {
private static final Logger logger = LoggerFactory.getLogger(WithDrawQuerySignUtil.class);
    
    public static NameValuePair[] withDrawQuery(NnMerchantVo merchantVo, WithDrawVo withDrawVo){
//        Date yeePayDate = withDrawVo.getYeePayDate();
//        Date yeePayDate = new Date();
        
//        String requestDateSectionBegin = dateFormat(yeePayDate,"yyyy-MM-dd 00:00:00");
//        String requestDateSectionEnd = dateFormat(yeePayDate,"yyyy-MM-dd 23:59:59");
        
        
        StringBuffer signBuffer = new StringBuffer();
        signBuffer.append(merchantVo.getYeeCustomerNumber() == null ? "" : merchantVo.getYeeCustomerNumber())
                  .append(withDrawVo.getOrderNo() == null ? "" : withDrawVo.getOrderNo())
                  .append(merchantVo.getMainCustomerNumber() == null ? "" : merchantVo.getMainCustomerNumber())
                  .append("1")
//                  .append(requestDateSectionBegin == null ? "" : requestDateSectionBegin)
//                  .append(requestDateSectionEnd == null ? "" : requestDateSectionEnd)
//                  .append(withDrawVo.getYeeExternalLd() == null ? "" : withDrawVo.getYeeExternalLd())
                  .append(withDrawVo.getTransferWay() == null ? "" : withDrawVo.getTransferWay());
        
        logger.info("结算签名前拼接参数为：{}", signBuffer.toString()); 
        String hmac = Digest.hmacSign(signBuffer.toString(), merchantVo.getKey());   
        logger.info("结算签名为：{}", hmac);
        
        NameValuePair[] param = {
             // 子商户号
                new NameValuePair("customerNumber", merchantVo.getYeeCustomerNumber()),

                new NameValuePair("externalNo", withDrawVo.getOrderNo()),
                // 系统商编号
                new NameValuePair("mainCustomerNumber", merchantVo.getMainCustomerNumber()),
                // 分页-页码
                new NameValuePair("pageNo", "1"),

                // 请求时间起始时间
//                new NameValuePair("requestDateSectionBegin", requestDateSectionBegin),
                // 请求时间结束时间
//                new NameValuePair("requestDateSectionEnd", requestDateSectionEnd),
                
//                new NameValuePair("serialNo", withDrawVo.getYeeExternalLd()),
                
                new NameValuePair("transferWay", withDrawVo.getTransferWay()),

                // 签名串
                new NameValuePair("hmac", hmac),
        };
        
        return param;
    }
    
    private static String dateFormat(Date date, String pattern){
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String formatDateStr = format.format(date);
        return formatDateStr;
    }
}
