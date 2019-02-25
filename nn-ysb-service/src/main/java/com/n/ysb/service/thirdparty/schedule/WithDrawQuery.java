package com.n.ysb.service.thirdparty.schedule;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import com.n.ysb.service.thirdparty.TransferWay;
import com.n.ysb.service.thirdparty.vo.WithDrawVo;
import com.n.ysb.service.thirdparty.yeepay.IYeePayRequestService;

@Service
public class WithDrawQuery {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private IOrderService orderService;
    
    @Autowired
    private IYeePayRequestService yeePayRequestService;
    
    @Autowired
    private IMerchantService merchantService;
    
    @Autowired
    private OrderStatusLog orderStatusLog;

    public void withDrawQuery() {
        logger.info("结算查询定时器开始了"+new Date());
        List<NnOrderVo> unWithDrawSuccessOrderVo = orderService.getUnWithDrawSuccessOrderVo();
        
        for (NnOrderVo orderVo : unWithDrawSuccessOrderVo) {
            NnMerchantVo merchantVo = merchantService.getMerchantByLoginMobile(orderVo.getMerchantMobile());
            
            WithDrawVo withDrawVo = new WithDrawVo();
            withDrawVo.setOrderNo(orderVo.getOrderNo());
            withDrawVo.setTransferWay(TransferWay.T0.getCode());
            
            JSONObject retJson = yeePayRequestService.withDrawQuery(merchantVo, withDrawVo);
            if(retJson.get("code").equals("0000")){
                JSONObject withDrawJson = JSONObject.fromObject(JSONArray.fromObject(retJson.getString("transferRequests")).get(0));
                if(withDrawJson.getString("transferStatus").equals("SUCCESSED")){
                    //结算成功
                    String yeeReceiver = withDrawJson.getString("receiver");
                    String yeeReceiverBankcardNo = withDrawJson.getString("receiverBankCardNo");
                    String yeeT0WithdrawFee = withDrawJson.getString("basicFee");
                    String yeeT0WithdrawExfee = withDrawJson.getString("extTargetFee");
                    String yeeWithdrawHandleDate = withDrawJson.getString("handleTime");
                    String yeeWithdrawAmt = withDrawJson.getString("amount");
                    String yeeWithdrawActualAmt = withDrawJson.getString("actualAmount");
                    String yeeWithdrawStatus = withDrawJson.getString("transferStatus");
                    
                    orderVo.setYeeReceiver(yeeReceiver);
                    orderVo.setYeeReceiverBankcardNo(yeeReceiverBankcardNo);
                    orderVo.setYeeT0WithdrawFee(new BigDecimal(yeeT0WithdrawFee));
                    orderVo.setYeeT0WithdrawExfee(new BigDecimal(yeeT0WithdrawExfee));
                    try {
                        orderVo.setYeeWithdrawHandleDate(DateUtils.parseDate(yeeWithdrawHandleDate, new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"}));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    
                    orderVo.setYeeWithdrawAmt(new BigDecimal(yeeWithdrawAmt));
                    orderVo.setYeeWithdrawActualAmt(new BigDecimal(yeeWithdrawActualAmt));
                    orderVo.setYeeWithdrawStatus(yeeWithdrawStatus);
                    orderVo.setOrderStatus(OrderStatus.withdraw_suc.getCode());
                    
                    boolean updateSuc = orderService.updateOrderVo(orderVo);
                    if(updateSuc){
                        orderStatusLog.logOrderWithdrawSuc(orderVo.getOrderNo(), "定时向易宝发送结算查询请求，更新单据状态为结算成功");
                    }
                }else{
                    //存日志
                    orderStatusLog.logOrderStatus(orderVo.getOrderNo(), OrderStatus.getOrderStatusByCode(orderVo.getOrderStatus()), "定时向易宝发送结算查询请求，返回json为："+retJson);
                }
            }
        }
        
    }
    
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 10);
        Date time = calendar.getTime();
        
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 30);
        Date time2 = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(time));
        System.out.println(df.format(time2));
    }
}
