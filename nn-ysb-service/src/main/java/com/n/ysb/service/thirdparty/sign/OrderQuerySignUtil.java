package com.n.ysb.service.thirdparty.sign;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.n.ysb.service.merchant.vo.NnMerchantVo;
import com.n.ysb.service.order.vo.NnOrderVo;

public class OrderQuerySignUtil {

    private static final Logger logger = LoggerFactory.getLogger(OrderQuerySignUtil.class);
    
    public static NameValuePair[] orderQuerySign(NnMerchantVo merchantVo, NnOrderVo orderVo){
        //处理时间
//        Date createDate = orderVo.getCreateDate();
//        String createTimeBegin = dateFormat(createDate,"yyyy-MM-dd 00:00:00");
//        String createTimeEnd = dateFormat(createDate,"yyyy-MM-dd 23:59:59");
        
        StringBuffer signBuffer = new StringBuffer();
        signBuffer.append(merchantVo.getMainCustomerNumber() == null ? "" : merchantVo.getMainCustomerNumber())
                  .append(merchantVo.getYeeCustomerNumber() == null ? "" : merchantVo.getYeeCustomerNumber())
                  .append(orderVo.getOrderNo() == null ? "" : orderVo.getOrderNo())
//                  .append(createTimeBegin == null ? "" : createTimeBegin)
//                  .append(createTimeEnd == null ? "" : createTimeEnd)
                  .append("1");
        
        logger.info("交易查询签名前拼接参数为：{}", signBuffer.toString()); 
        String hmac = Digest.hmacSign(signBuffer.toString(), merchantVo.getKey());   
        logger.info("交易查询签名为：{}", hmac);
        
        NameValuePair[] param = {
                // 系统商商户编号
                new NameValuePair("mainCustomerNumber", merchantVo.getMainCustomerNumber()),
                // 子商户编号
                new NameValuePair("customerNumber", merchantVo.getYeeCustomerNumber()),
                // 收款订单号
                new NameValuePair("requestId", orderVo.getOrderNo()),
                // 请求时间开始时间
//                new NameValuePair("createTimeBegin", createTimeBegin),
                // 请求时间结束时间
//                new NameValuePair("createTimeEnd", createTimeEnd),
                // 分页-页码
                new NameValuePair("pageNo", "1"),
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