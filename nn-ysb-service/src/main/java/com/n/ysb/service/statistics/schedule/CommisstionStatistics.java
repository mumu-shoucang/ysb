package com.n.ysb.service.statistics.schedule;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n.ysb.service.merchant.IMerchantService;
import com.n.ysb.service.merchant.po.NnMerchant;
import com.n.ysb.service.merchant.vo.NnMerchantVo;
import com.n.ysb.service.order.IOrderService;
import com.n.ysb.service.order.po.NnOrder;
import com.n.ysb.service.referrer.mapper.NnReferrerMapper;
import com.n.ysb.service.referrer.po.NnReferrer;
import com.n.ysb.service.statistics.mapper.NnStatisticsMapper;
import com.n.ysb.service.statistics.po.NnStatistics;
import com.n.ysb.service.transfer.impl.TransferStatus;
@Service
public class CommisstionStatistics {
    
    @Autowired
    private IOrderService orderService;
    @Autowired
    private NnStatisticsMapper statisticsMapper;
    @Autowired
    private NnReferrerMapper referrerMapper;
    @Autowired
    private IMerchantService merchantService;
    

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    public void commisstionStatistics() throws ParseException{
        List<NnReferrer> allReferrer = this.getAllReferrer();
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
        Date time = calendar.getTime();
        Date parse = format.parse(format.format(time));
        
        for (NnReferrer referrer : allReferrer) {
            NnStatistics refStatistics = new NnStatistics();
            
            //推荐人子商户处理
            List<NnMerchantVo> merchantByRefSign = merchantService.getMerchantByRefSign(referrer.getRefSign());
            long regMerchant = 0;
            long authMerchant = 0;
            for (NnMerchantVo merchantVo : merchantByRefSign) {
                String formatDate = format.format(time);
                String createDate = format.format(merchantVo.getCreateDate());
                String authDate ="";
                if(merchantVo.getAuthDate()!=null){
                    authDate = format.format(merchantVo.getAuthDate());
                }
                
                if(formatDate.equals(createDate)){
                    regMerchant+=1;
                }
                if(formatDate.equals(authDate)){
                    authMerchant+=1;
                }
                
            }
            refStatistics.setRegUserCount(regMerchant);
            refStatistics.setAntuUserCount(authMerchant);
            
            
            //推荐人子商户订单处理
            List<NnOrder> orderList = orderService.getOrderByRefSign(referrer.getRefSign());
            BigDecimal orderAmt = new BigDecimal(0);
            BigDecimal tradeCommisstion = new BigDecimal(0);
            BigDecimal withdrawCommisstion = new BigDecimal(0);
            
            
            for (NnOrder order : orderList) {
                orderAmt = orderAmt.add(order.getOrderAmt());
                tradeCommisstion = tradeCommisstion.add(order.getTradeCommisstion());
                withdrawCommisstion = withdrawCommisstion.add(order.getWithdrawCommisstion());
            }
            //当天成功订单
            refStatistics.setTradeSucCount(Long.valueOf(orderList.size()));
            //此处复用，原统计总佣金金额，作为总交易金额
            refStatistics.setStatisticsAmt(orderAmt.setScale(2,BigDecimal.ROUND_HALF_UP));
            refStatistics.setTradeCommisstion(tradeCommisstion.setScale(2,BigDecimal.ROUND_HALF_UP));
            refStatistics.setWithdrawCommisstion(withdrawCommisstion.setScale(2,BigDecimal.ROUND_HALF_UP));
            //佣金比例
            refStatistics.setTradeCommisstionRate(referrer.getCommissionRate());
            
            // 有交易或者用户时，才进行存储
            if(!(orderList.size() == 0 && merchantByRefSign.size() == 0)) {
            		String yeeCustomerNumber = referrer.getYeeCustomerNumber();
            		if(StringUtils.isBlank(yeeCustomerNumber)) {
            			NnMerchant merchant =  merchantService.getYeeCustomerNumber(referrer.getIdCard());
            			yeeCustomerNumber = Objects.toString(merchant.getYeeCustomerNumber(), "");
            		}
                refStatistics.setStatisticsDate(parse);
                refStatistics.setStatisticsType(referrer.getRefSign());
                refStatistics.setYeeCustomerNumber(yeeCustomerNumber);
                refStatistics.setCommissionTransferStat(TransferStatus.untransfer.getCode());
                
                statisticsMapper.insertSelective(refStatistics);
            }
        }
    }
    
    private List<NnReferrer> getAllReferrer(){
        List<NnReferrer> selectByExample = referrerMapper.selectByExample(null);
        return selectByExample;
    }
}
