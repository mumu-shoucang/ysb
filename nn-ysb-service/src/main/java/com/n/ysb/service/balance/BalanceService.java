package com.n.ysb.service.balance;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n.ysb.service.thirdparty.vo.BalanceType;
import com.n.ysb.service.thirdparty.yeepay.IYeePayRequestService;

@Service
public class BalanceService {

	@Autowired
	private IYeePayRequestService yeePayRequestService;
	
	public Map<String, Object> balanceQuery(String yeeCustomerNumber, String balanceType) {
		Map<String, Object> qmap = yeePayRequestService.balanceQuery(yeeCustomerNumber, BalanceType.getValueByCode(balanceType));
		return qmap;
	}
}
