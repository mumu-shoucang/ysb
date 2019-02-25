package com.n.ysb.service.order.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.n.ysb.service.business.OrderStatus;
import com.n.ysb.service.business.WithdrawStatus;
import com.n.ysb.service.globalCfg.IGlobalCfgService;
import com.n.ysb.service.globalCfg.vo.NnGlobalCfgVo;
import com.n.ysb.service.merchant.IMerchantService;
import com.n.ysb.service.merchant.mapper.NnMerchantMapper;
import com.n.ysb.service.merchant.po.NnMerchant;
import com.n.ysb.service.order.IOrderService;
import com.n.ysb.service.order.mapper.NnOrderMapper;
import com.n.ysb.service.order.po.NnOrder;
import com.n.ysb.service.order.po.NnOrderExample;
import com.n.ysb.service.order.po.NnOrderExample.Criteria;
import com.n.ysb.service.order.vo.NnOrderVo;
import com.n.ysb.service.statistics.schedule.CommisstionStatistics;
import com.n.ysb.service.statistics.schedule.MainCustomerStatistics;
import com.n.ysb.service.thirdparty.schedule.OrderQuery;
import com.n.ysb.service.thirdparty.schedule.WithDrawQuery;
import com.n.ysb.service.thirdparty.sms.SMS;
import com.n.ysb.service.thirdparty.sms.SMS2;
import com.n.ysb.service.util.AESUtil;
import com.n.ysb.service.withdraw.mapper.NnYeecustomerWithdrawMapper;
import com.n.ysb.service.withdraw.po.NnYeecustomerWithdraw;

@RunWith(SpringJUnit4ClassRunner.class)    
@ContextConfiguration(locations = {"classpath*:springContext.xml"})
public class OrderServiceImplTest {
    
    @Autowired
    private IOrderService orderServcie;
    
    @Autowired
    private NnOrderMapper orderMapper;
    
    @Autowired
    private OrderQuery orderQuery;
    
    @Autowired
    private WithDrawQuery withDrawQuery;
    
    @Autowired
    private MainCustomerStatistics mainCustomerStatistics;
    
    @Autowired
    private CommisstionStatistics commisstionStatistics;
    
    @Autowired
    private SMS2 sms2;
    
    @Autowired
    private SMS sms;
    
    @Autowired
    private IGlobalCfgService globalCfgService;
    
    @Autowired
    private NnMerchantMapper merchantMapper;
    
    @Autowired
    private NnYeecustomerWithdrawMapper withdrawMapper;
    
    @Test
    public void balanceQuery(){
        String balanceQuery = sms2.balanceQuery();
        System.out.println("短信剩余余额为："+balanceQuery);
    }
    
    @Test
    public void get(){
        NnGlobalCfgVo redisGlobalCfgVo = globalCfgService.getRedisGlobalCfgVo();
        System.out.println(redisGlobalCfgVo);
    }
    @Test
    public void sendSms(){
        sms2.sendSMSCode("18911257880", "陈环123456");
    }
    
    @Test
    public void sendSms2(){
        sms.sendSMSCode("18911257880", "123456");
    }
    
    @Test
    public void commisstionStatistics() throws ParseException {
        commisstionStatistics.commisstionStatistics();
    }
    
    @Test
    public void mainCustomerStatistics() throws ParseException {
        mainCustomerStatistics.mainCustomerStatistics();
    }
    
    @Test
    public void orderQuery() {
        orderQuery.orderQuery();
    }
    
    @Test
    public void withDrawQuery() {
        withDrawQuery.withDrawQuery();
    }
    
    @Test
    public void testGetUnSuccessOrderVo() {
        List<NnOrderVo> unSuccessOrderVo = orderServcie.getUnSuccessOrderVo();
        
        for (NnOrderVo nnOrderVo : unSuccessOrderVo) {
            System.out.println(nnOrderVo.toString());
        }
    }
    
    @Test
    public void testGetUnWithDrawSuccessOrderVo() {
        List<NnOrderVo> unSuccessOrderVo = orderServcie.getUnWithDrawSuccessOrderVo();
        
        for (NnOrderVo nnOrderVo : unSuccessOrderVo) {
            System.out.println(nnOrderVo.toString());
        }
    }

    @Test
    public void testUpdateOrderVo() throws ParseException {
        Calendar calendar2 = Calendar.getInstance();
//        calendar2.set(Calendar.MINUTE, calendar2.get(Calendar.MINUTE) -10);
        
        
        NnOrderExample example = new NnOrderExample();
        Criteria criteria = example.createCriteria();
        Date time1 = calendar2.getTime();
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        criteria.andCreateDateLessThan(calendar2.getTime());
        
        calendar2.set(Calendar.DATE, calendar2.get(Calendar.DATE) - 1);
        Date time2 = calendar2.getTime();
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd 23:59:00");
        criteria.andCreateDateGreaterThan(df2.parse(df2.format(time2)));
        
        List<NnOrder> selectByExample = orderMapper.selectByExample(example);
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
        Date before1Date = calendar.getTime();
        
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 10);
        Date before15Mi = calendar.getTime();
        
        for (NnOrder order : selectByExample) {
            if(order.getTradeCommisstion()!=null || order.getTradeCommisstion().compareTo(new BigDecimal("0")) == 0){
                NnOrderVo orderVo = new NnOrderVo();
                BeanUtils.copyProperties(order, orderVo);
                orderVo.setCreateDate(before1Date);
                orderVo.setOrderStatus(OrderStatus.withdraw_suc.getCode());
//                orderVo.setYeeExternalLd("");
//                orderVo.setYeePayDate(null);
//                orderVo.setYeePayStatus(null);
//                orderVo.setYeeTradeFee(new BigDecimal("0.02"));
                
//                orderVo.setYeeWithdrawStatus("SUCCESSED");
//                orderVo.setYeeWithdrawHandleDate(before1Date15Mi);
                
//                orderVo.setYeeT0WithdrawFee(new BigDecimal(2));
                
//                BigDecimal subtract = orderVo.getOrderAmt().subtract(new BigDecimal("0.02"));
                
//                BigDecimal setScale = subtract.multiply(new BigDecimal("0.005")).setScale(2,BigDecimal.ROUND_HALF_DOWN);
//                orderVo.setYeeT0WithdrawExfee(setScale);
                orderVo.setYeeWithdrawHandleDate(before15Mi);
                
                orderServcie.updateOrderVo(orderVo);
            }
        }
    }
    
    @Test
    public void testInsertMerchant(){
        
        for(int i=0;i<=9;i++){
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
            Date before1Date = calendar.getTime();
            
            NnMerchant merchant = new NnMerchant();
            merchant.setCreateDate(before1Date);
            merchant.setLoginMobile("1891125111"+i);
            merchant.setRefSign("changhongrui");
            merchant.setBankAccountName("chenhuan00"+i);
            if(i%2==0){
                calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 15);
                merchant.setAuthDate(calendar.getTime());
            }
            
            String encrypt = AESUtil.encrypt("111111", "30TWH7TG72QBMXSA");
            merchant.setLoginPwd(encrypt);
            merchant.setFeeSetFlag("0");
            
            merchantMapper.insertSelective(merchant);
        }
        
        
    }
    
    @Test
    public void testInsertWithdrawOrder(){
        for(int i=1;i<=132;i++){
            NnYeecustomerWithdraw withdraw = new NnYeecustomerWithdraw();
            withdraw.setCreateDate(new Date());
            withdraw.setOrderNo("test000"+i);
            withdraw.setWithdrawAmt(new BigDecimal("15").add(new BigDecimal(i)));
            withdraw.setWithdrawStatus(WithdrawStatus.withdrawing.getCode());
            withdraw.setYeeCustomerNumber("10023789149");
            
            withdrawMapper.insertSelective(withdraw);
        }
        
        
    }

}
