package com.n.ysb.service.business.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditBankService {

	private static final Logger log = LoggerFactory.getLogger(CreditBankService.class);
	
	@Autowired
	private BankAliService bankAliService;
	
	public String getCCBankCodeFromAli(String bankNo) {
		String bankCode =  bankAliService.getBankCodeFromAli(bankNo, "CC");
		log.info("get CC bankcode from ali: {} --{}", bankNo, bankCode);
		return bankCode;
	}
	
}
