package com.n.ysb.service.thirdparty.schedule;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n.ysb.service.business.OrderStatus;
import com.n.ysb.service.business.log.OrderStatusLog;
import com.n.ysb.service.merchant.IMerchantService;
import com.n.ysb.service.merchant.vo.NnMerchantVo;
import com.n.ysb.service.order.IOrderService;
import com.n.ysb.service.order.vo.NnOrderVo;
import com.n.ysb.service.thirdparty.yeepay.IYeePayRequestService;

@Service
public class OrderQuery {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private IOrderService orderService;
    
    @Autowired
    private IYeePayRequestService yeePayRequestService;
    
    @Autowired
    private IMerchantService merchantService;
    
    @Autowired
    private OrderStatusLog orderStatusLog;

    public void orderQuery() {
        logger.info("订单查询定时器开始了");
        List<NnOrderVo> unSuccessOrderVo = orderService.getUnSuccessOrderVo();
        for (NnOrderVo orderVo : unSuccessOrderVo) {
            NnMerchantVo merchantVo = merchantService.getMerchantByLoginMobile(orderVo.getMerchantMobile());
            JSONObject retJson = yeePayRequestService.orderQuery(merchantVo, orderVo);
            if(retJson.get("code").equals("0000")){
                JSONObject orderJson = JSONObject.fromObject(JSONArray.fromObject(retJson.getString("tradeReceives")).get(0));
                if(orderJson.getString("status").equals("SUCCESS")){
                    String externalId = orderJson.getString("externalId");
                    String status = orderJson.getString("status");
                    String payTime = orderJson.getString("payTime");
                    String fee = orderJson.getString("fee");
                    
                    orderVo.setYeeExternalLd(externalId);
                    orderVo.setYeePayStatus(status);
                    
                    try {
                        orderVo.setYeePayDate(DateUtils.parseDate(payTime, new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"}));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    
                    orderVo.setYeePayStatus(status);
                    orderVo.setYeeTradeFee(new BigDecimal(fee));
                    orderVo.setOrderStatus(OrderStatus.pay_suc.getCode());
                    
                    boolean updateSuc = orderService.updateOrderVo(orderVo);
                    if(updateSuc){
                        orderStatusLog.logOrderPaySuc(orderVo.getOrderNo(), "定时向易宝发送订单查询请求，更新单据状态为支付成功");
                    }
                }else  if(orderJson.getString("status").equals("INIT") || orderJson.getString("status").equals("FAIL")){
                    orderVo.setOrderStatus(OrderStatus.trade_closed.getCode());
                    boolean updateSuc = orderService.updateOrderVo(orderVo);
                    if(updateSuc){
                        orderStatusLog.logOrderStatus(orderVo.getOrderNo(), OrderStatus.getOrderStatusByCode(orderVo.getOrderStatus()), "定时向易宝发送订单查询请求，返回json为："+retJson);
                    }
                }else{
                    orderStatusLog.logOrderStatus(orderVo.getOrderNo(), OrderStatus.getOrderStatusByCode(orderVo.getOrderStatus()), "定时向易宝发送订单查询请求，返回json为："+retJson);
                }
            }
        }
        
        
    }
    
    public static void main(String[] args) {
        String ss = "{\"actualAmount\":0.98,\"amount\":1.98,\"basicFee\":1,\"batchNo\":\"153147625482300\",\"customerNumber\":\"10023497339\",\"extTargetFee\":0,\"failReason\":\"\",\"handleTime\":\"2018-07-13 18:04:16\",\"receiver\":\"陈*\",\"receiverBank\":\"招商银行\",\"receiverBankCardNo\":\"622588******7469\",\"requestNo\":\"hlxwkjfzyxgs0007\",\"requestTime\":\"2018-07-13 18:04:14\",\"transferStatus\":\"SUCCESSED\"}";
        JSONObject fromObject = JSONObject.fromObject(ss);
        System.out.println(fromObject.get("extTargetFee"));
        System.out.println(fromObject.get("basicFee"));
        System.out.println(fromObject.get("amount"));
        
        Map map = JSONObject.fromObject(ss);
        System.out.println(map.get("extTargetFee"));
        System.out.println(map.get("basicFee"));
        System.out.println(map.get("amount"));
    }
}
