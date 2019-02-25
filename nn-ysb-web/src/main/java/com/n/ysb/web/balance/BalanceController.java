package com.n.ysb.web.balance;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.n.ysb.service.balance.BalanceService;
import com.n.ysb.web.base.BaseController;
import com.n.ysb.web.base.SimpleResponse;

@Controller
@RequestMapping("balance")
public class BalanceController extends BaseController {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BalanceService balanceService;
	
	@RequestMapping("balanceQuery")
	@ResponseBody
	public SimpleResponse singleTransfer(String yeeCustomerNumber, String balanceType) {
		Map<String, Object> ret = balanceService.balanceQuery(yeeCustomerNumber, balanceType);
		log.info("{}-查询余额返回-{}", ret);
		return SimpleResponse.suc(ret);
	}
	
}