package com.n.ysb.web.test;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.n.ysb.service.merchant.IMerchantService;
import com.n.ysb.service.merchant.vo.NnMerchantVo;
import com.n.ysb.service.order.po.NnOrder;
import com.n.ysb.service.order.vo.NnOrderVo;
import com.n.ysb.service.referrer.vo.NnReferrerVo;
import com.n.ysb.service.thirdparty.WithdrawType;
import com.n.ysb.service.thirdparty.sms.SMS2;
import com.n.ysb.service.thirdparty.vo.CustomerInforUpdateVo;
import com.n.ysb.service.thirdparty.vo.ReceiveVo;
import com.n.ysb.service.thirdparty.vo.RegisterVo;
import com.n.ysb.service.thirdparty.vo.WithDrawVo;
import com.n.ysb.service.thirdparty.yeepay.IYeePayRequestService;
import com.n.ysb.web.base.BaseController;
import com.n.ysb.web.base.SimpleResponse;

@Controller
@RequestMapping("interfaceTest")
public class TestController extends BaseController {
    
    @Autowired
    private IYeePayRequestService yeePayRequestService;
    
    @Autowired
    private IMerchantService merchantService;
    
    @Autowired
    private SMS2 sms2;
    
    @RequestMapping("register")
    @ResponseBody
    public SimpleResponse register(){
        RegisterVo registerVo = new RegisterVo();
        registerVo.setMailStr("chenhuan@2858.com");
        registerVo.setRequestId("chenhuan0001");
        registerVo.setCustomerType("PERSON");
        registerVo.setBindMobile("18911257880");
        registerVo.setSignedName("陈环");
        registerVo.setLinkMan("陈环");
        registerVo.setIdCard("211121199004291019");
        registerVo.setLegalPerson("陈环");
        registerVo.setMinSettleAmount("1");
        registerVo.setRiskReserveDay("0");
//        merchantVo.setBankAccountType("PrivateCash");
        registerVo.setBankAccountNo("6225880137717469");
        registerVo.setBankName("招商银行");
        registerVo.setBankAccountName("陈环");
        registerVo.setManualSettle("Y");
        registerVo.setBankCardPhoto(new File("D:/test.png"));
//        merchantVo.setBusinessLicensePhoto(new File("D:/test.png"));
        registerVo.setIdCardPhoto(new File("D:/test.png"));
        registerVo.setIdCardBackPhoto(new File("D:/test.png"));
        registerVo.setPersonPhoto(new File("D:/test.png"));
        
        Map<String, Object> register = yeePayRequestService.register(registerVo);
        
        return SimpleResponse.suc(register);
    }
    
    @RequestMapping("infoUpdate")
    @ResponseBody
    public SimpleResponse infoUpdate(){
        CustomerInforUpdateVo customerInforUpdateVo = new CustomerInforUpdateVo();
        customerInforUpdateVo.setMerchantCode("10023497339");
        
        customerInforUpdateVo.setModifyType("2");
//        customerInforUpdateVo.setBankAccountNo("6225880137717469");
//        customerInforUpdateVo.setBankName("招商银行");
//        customerInforUpdateVo.setBankAccountNo("6215590200001791888");
//        customerInforUpdateVo.setBankName("工商银行");
        
        customerInforUpdateVo.setModifyType("6");
        customerInforUpdateVo.setMailStr("chenhuan@2858.com");
        customerInforUpdateVo.setBindMobile("18911257880");
        
        Map<String, Object> register = yeePayRequestService.customerInforUpdate(customerInforUpdateVo);
        
        return SimpleResponse.suc(register);
    }
    
    @RequestMapping("receive")
    @ResponseBody
    public SimpleResponse receive(){
        RegisterVo registerVo = new RegisterVo();
//        merchantVo.setMailStr("chenhuan@2858.com");
        registerVo.setRequestId("chenhuan0004");
        registerVo.setCustomerType("PERSON");
        registerVo.setBindMobile("18911257880");
        registerVo.setSignedName("陈环");
        registerVo.setLinkMan("陈环");
        registerVo.setIdCard("211121199004291019");
        registerVo.setLegalPerson("陈环");
        registerVo.setMinSettleAmount("1");
        registerVo.setRiskReserveDay("0");
//        merchantVo.setBankAccountType("PrivateCash");
        registerVo.setBankAccountNo("6225880137717469");
        registerVo.setBankName("招商银行");
        registerVo.setBankAccountName("陈环");
//        merchantVo.setManualSettle("Y");
        registerVo.setBankCardPhoto(new File("D:/test.png"));
//        merchantVo.setBusinessLicensePhoto(new File("D:/test.png"));
        registerVo.setIdCardPhoto(new File("D:/test.png"));
//        merchantVo.setIdCardBackPhoto(new File("D:/test.png"));
        registerVo.setPersonPhoto(new File("D:/test.png"));
        registerVo.setMerchantCode("10023497339");
        registerVo.setYeeCustomerNumber("10023497339");
        
        
        NnOrder order = new NnOrder();
        order.setOrderNo("hlxwkjfzyxgs0012");
        order.setOrderAmt(new BigDecimal(1));
        order.setMcc("5311");
        order.setCreditCardNo("6225768749923098");
        ReceiveVo receiveVo = new ReceiveVo(order);
        Map<String, Object> register = yeePayRequestService.receive(registerVo,receiveVo);
        
        return SimpleResponse.suc(register);
    }
    
    @RequestMapping("orderQuery")
    @ResponseBody
    public SimpleResponse orderQuery(){
        RegisterVo registerVo = new RegisterVo();
//        merchantVo.setMailStr("chenhuan@2858.com");
        registerVo.setRequestId("chenhuan0003");
        registerVo.setCustomerType("PERSON");
        registerVo.setBindMobile("18911257880");
        registerVo.setSignedName("陈环");
        registerVo.setLinkMan("陈环");
        registerVo.setIdCard("211121199004291019");
        registerVo.setLegalPerson("陈环");
        registerVo.setMinSettleAmount("1");
        registerVo.setRiskReserveDay("0");
//        merchantVo.setBankAccountType("PrivateCash");
        registerVo.setBankAccountNo("6225880137717469");
        registerVo.setBankName("招商银行");
        registerVo.setBankAccountName("陈环");
//        merchantVo.setManualSettle("Y");
        registerVo.setBankCardPhoto(new File("D:/test.png"));
//        merchantVo.setBusinessLicensePhoto(new File("D:/test.png"));
        registerVo.setIdCardPhoto(new File("D:/test.png"));
//        merchantVo.setIdCardBackPhoto(new File("D:/test.png"));
        registerVo.setPersonPhoto(new File("D:/test.png"));
        registerVo.setMerchantCode("10023497339");
        
        NnOrderVo orderVo = new NnOrderVo();
        orderVo.setOrderNo("hlxwkjfzyxgs0007");
        orderVo.setOrderAmt(new BigDecimal(1));
        orderVo.setMcc("5311");
        orderVo.setCreditCardNo("6225768749923098");
        orderVo.setCreateDate(new Date());
        
//        Map<String, Object> register = yeePayRequestService.orderQuery(registerVo,orderVo);
        
        return SimpleResponse.suc(null);
    }
    
    @RequestMapping("feeSet")
    @ResponseBody
    public SimpleResponse feeSet(){
        NnMerchantVo merchantVo = new NnMerchantVo();
        merchantVo.setMerchantCode("10023497339");
        Map<String, Object> tradeFeeSet = yeePayRequestService.tradeFeeSet(merchantVo);
        Map<String, Object> t0WithdrawFeeSet = yeePayRequestService.t0WithdrawFeeSet(merchantVo);
        Map<String, Object> t0WithdrawWorkdayFeeSet = yeePayRequestService.t0WithdrawWorkdayFeeSet(merchantVo);
        Map<String, Object> t0WithdrawNonworkdayFeeSet = yeePayRequestService.t0WithdrawNonworkdayFeeSet(merchantVo);
        
        
        
//        return SimpleResponse.suc(tradeFeeSet);
        return SimpleResponse.suc(t0WithdrawFeeSet);
//        return SimpleResponse.suc(t0WithdrawWorkdayFeeSet);
//        return SimpleResponse.suc(t0WithdrawNonworkdayFeeSet);
    }
    
    @RequestMapping("withDraw")
    @ResponseBody
    public SimpleResponse withDraw(){
        NnMerchantVo merchantVo = new NnMerchantVo();
        merchantVo.setMerchantCode("10023497339");
        
        WithDrawVo withDrawVo = new WithDrawVo();
        withDrawVo.setTransferWay("1");
        withDrawVo.setExternalNo("test002");
        withDrawVo.setOrderAmt(new BigDecimal("1.96"));
        
        
        Map<String, Object> withDraw = yeePayRequestService.withDraw(merchantVo, withDrawVo, WithdrawType.order_hand_withdraw.getCode());
        return SimpleResponse.suc(withDraw);
    }
    
    @RequestMapping("withDrawQuery")
    @ResponseBody
    public SimpleResponse withDrawQuery(){
        NnMerchantVo merchantVo = new NnMerchantVo();
        merchantVo.setMerchantCode("10023497339");
        
        WithDrawVo withDrawVo = new WithDrawVo();
        withDrawVo.setYeeExternalLd("711867686527283200");
        withDrawVo.setTransferWay("1");
        withDrawVo.setOrderNo("test002");
        
//        Map<String, Object> withDrawQuery = yeePayRequestService.withDrawQuery(merchantVo, withDrawVo);
        
        return SimpleResponse.suc(null); 
    }
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @RequestMapping("feeSetAll")
    @ResponseBody
    public SimpleResponse feeSetAll(){
        NnMerchantVo merchantByLoginMobile = merchantService.getMerchantByLoginMobile("18911257880");
//        for (int i = 1; i <= 10000; i++) {
//            logger.info("第==========="+i+"次循环");
            yeePayRequestService.feeSet(merchantByLoginMobile);
//        }
        
        return SimpleResponse.suc(merchantByLoginMobile);
    }
    
    @RequestMapping("sendSms")
    @ResponseBody
    public SimpleResponse sendSms(){
        
        return SimpleResponse.suc("18911257880");
    }
    
    
    
    public static String formatDate(Date date, String pattern) {
        if (date == null) throw new IllegalArgumentException("date is null");
        if (pattern == null) throw new IllegalArgumentException("pattern is null");
        
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return formatter.format(date);
    }
    
    public static void main(String[] args) {
        
        NnReferrerVo vo = new NnReferrerVo();
        vo.setCommissionRate(new BigDecimal("0.5"));
        
        NnOrder po = new NnOrder();
        po.setOrderAmt(new BigDecimal("20.71"));
      //佣金比例
        BigDecimal commissionRate = vo.getCommissionRate().divide(new BigDecimal(100)).setScale(6,BigDecimal.ROUND_DOWN);
        
        BigDecimal commisstion = po.getOrderAmt().multiply(commissionRate).setScale(2,BigDecimal.ROUND_DOWN);
        System.out.println(commisstion);    
        }
    
}
