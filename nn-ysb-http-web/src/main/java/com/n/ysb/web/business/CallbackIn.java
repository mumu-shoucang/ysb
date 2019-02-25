package com.n.ysb.web.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.n.ysb.service.business.callbackin.CallbackService;
import com.n.ysb.service.business.callbackin.vo.PayBackVo;
import com.n.ysb.service.business.callbackin.vo.WithdrawBackVo;

@RestController
@RequestMapping("callbackin")
public class CallbackIn {

	private static final Logger log = LoggerFactory.getLogger(CallbackIn.class);
	
	@Autowired
	private CallbackService callbackService;
	
	@RequestMapping("pay")
	public String payCallback(PayBackVo pars) {
		log.info("接到易宝侧支付成功回调：{}", pars.toString());
		callbackService.payCallback(pars);
		return "SUCCESS";
	}
	
	@RequestMapping("withdraw")
	public String withdrawCallback(WithdrawBackVo pars) {
		log.info("接到易宝侧结算成功回调：{}", pars.toString());
		callbackService.withdrawCallback(pars);
		return "SUCCESS";
	}
	
	@RequestMapping("merchantWithdraw")
    public String merchantWithdrawCallback(WithdrawBackVo pars) {
        log.info("接到易宝侧子商户结算成功回调：{}", pars.toString());
        callbackService.merchantWithdrawCallback(pars);
        return "SUCCESS";
    }
	
}
