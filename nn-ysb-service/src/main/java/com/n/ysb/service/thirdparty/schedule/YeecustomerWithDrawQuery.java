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
import com.n.ysb.service.business.WithdrawStatus;
import com.n.ysb.service.merchant.vo.NnMerchantVo;
import com.n.ysb.service.thirdparty.TransferWay;
import com.n.ysb.service.thirdparty.vo.WithDrawVo;
import com.n.ysb.service.thirdparty.yeepay.IYeePayRequestService;
import com.n.ysb.service.withdraw.impl.WithdrawService;
import com.n.ysb.service.withdraw.mapper.NnYeecustomerWithdrawLogMapper;
import com.n.ysb.service.withdraw.po.NnYeecustomerWithdraw;
import com.n.ysb.service.withdraw.po.NnYeecustomerWithdrawLog;

@Service
public class YeecustomerWithDrawQuery {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private WithdrawService withdrawService;
    
    @Autowired
    private IYeePayRequestService yeePayRequestService;
    
    @Autowired
    private NnYeecustomerWithdrawLogMapper withdrawLogMapper;
    
    public void yeecustomerWithDrawQuery() {
        logger.info("子商户结算查询定时器开始了"+new Date());
        List<NnYeecustomerWithdraw> unWithDrawSuccessOrderVo = withdrawService.getUnWithDrawSuccessOrderVo();
        
        for (NnYeecustomerWithdraw withdraw : unWithDrawSuccessOrderVo) {
            NnMerchantVo merchantVo = new NnMerchantVo();
            merchantVo.setYeeCustomerNumber(withdraw.getYeeCustomerNumber());
            
            WithDrawVo withDrawVo = new WithDrawVo();
            withDrawVo.setOrderNo(withdraw.getOrderNo());
            withDrawVo.setTransferWay(TransferWay.T0.getCode());
            
            JSONObject retJson = yeePayRequestService.withDrawQuery(merchantVo, withDrawVo);
            logger.info(""+((int)retJson.get("totalRecords")>0));
            if(retJson.get("code").equals("0000") && (int)retJson.get("totalRecords")>0){
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
                    String batchNo = withDrawJson.getString("batchNo");
                    
                    withdraw.setYeeReceiver(yeeReceiver);
                    withdraw.setYeeReceiverBankcardNo(yeeReceiverBankcardNo);
                    withdraw.setYeeT0WithdrawFee(new BigDecimal(yeeT0WithdrawFee));
                    withdraw.setYeeT0WithdrawExfee(new BigDecimal(yeeT0WithdrawExfee));
                    try {
                        withdraw.setYeeWithdrawHandleDate(DateUtils.parseDate(yeeWithdrawHandleDate, new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"}));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    
                    withdraw.setYeeWithdrawAmt(new BigDecimal(yeeWithdrawAmt));
                    withdraw.setYeeWithdrawActualAmt(new BigDecimal(yeeWithdrawActualAmt));
                    withdraw.setYeeWithdrawStatus(yeeWithdrawStatus);
                    withdraw.setWithdrawStatus(WithdrawStatus.withdraw_suc.getCode());
                    withdraw.setYeeExternalLd(batchNo);
                    
                    boolean updateSuc = withdrawService.updateWithdrawOrder(withdraw);
                    
                    if(updateSuc){
                        withdrawLog(withdraw.getOrderNo(), withdraw.getWithdrawStatus(), "定时向易宝发送子商户结算查询请求，更新单据状态为结算成功");
                    }
                }else{
                    //存日志
                    withdrawLog(withdraw.getOrderNo(), withdraw.getWithdrawStatus(), "定时向易宝发送子商户结算查询请求，返回json为："+retJson);
                }
            }
        }
        
    }
    
    private void withdrawLog(String orderNo, String withdrawStatus, String desc){
        NnYeecustomerWithdrawLog log = new NnYeecustomerWithdrawLog();
        log.setOrderNo(orderNo);
        log.setOpTime(new Date());
        log.setOpUser("system");
        log.setWithdrawStatus(withdrawStatus);
        log.setRemarks(desc);
        
        withdrawLogMapper.insertSelective(log);
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
