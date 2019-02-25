package com.n.ysb.web.smsBalance;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.n.ysb.service.thirdparty.sms.SMS2;
import com.n.ysb.web.base.BaseController;
import com.n.ysb.web.base.SimpleResponse;

@Controller
@RequestMapping("smsBalance")
public class SmsBalanceController extends BaseController {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SMS2 sms2;
	
	@RequestMapping("balanceQuery")
    @ResponseBody
    public SimpleResponse singleTransfer(String yeeCustomerNumber, String balanceType) {
        String balanceQueryRet = sms2.balanceQuery();
        
        log.info("{}-查询短信余额返回-{}", balanceQueryRet);
        return SimpleResponse.suc(balanceQueryRet);
    }
	
}