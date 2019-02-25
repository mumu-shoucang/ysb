package com.n.ysb.service.business.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.n.ysb.service.business.core.DebitBankService.Bank;

@Service
public class BankCodeName {
	
	private ConcurrentHashMap<String, String> banks = new ConcurrentHashMap<>();
	
	@Autowired
	private ApplicationContext appContext;
	
	@PostConstruct
	private void init () {
		
		// put into 15 debit bank
		Map<String, Bank> vos = appContext.getBeansOfType(Bank.class);
		for(Map.Entry<String, Bank> entry : vos.entrySet()) {
			Bank bank = entry.getValue();
			String bankCode = bank.getCode();
			String bankName = bank.getName();
			banks.put(bankCode, bankName);
		}
		
		// 覆盖浦发银行的名称
		banks.put("SPDB", "浦发银行");
		// put others
		banks.put("PSBC", "中国邮政储蓄银行");
		
		banks.put("SPABANK", "平安银行");
		banks.put("EGBANK", "恒丰银行");
		banks.put("BOHAIB", "渤海银行");
		banks.put("CZBANK", "浙商银行");
		
		banks.put("SHBANK", "上海银行");
		banks.put("HZCB", "杭州银行");
		banks.put("JSBANK", "江苏银行");
		banks.put("HSBANK", "徽商银行");
		banks.put("GCB", "广州银行");
		banks.put("DLB", "大连银行");
		banks.put("NJCB", "南京银行");
		banks.put("BJRCB", "北京农村商业银行");
		banks.put("SHRCB", "上海农村商业银行");
		banks.put("WZCB", "温州银行");
		banks.put("DZBANK", "德州银行");
		banks.put("TCCB", "天津银行");
		banks.put("QLBANK", "齐鲁银行");
		banks.put("ZZBANK", "郑州银行");
		banks.put("HBC", "湖北银行");
		banks.put("JHBANK", "金华银行");
		banks.put("LZYH", "兰州银行");
		banks.put("BOJZ", "锦州银行");
		banks.put("BOQH", "青海银行");
	}
	
	public String getNameByCode(String bankCode) {
		String bankName = banks.get(bankCode);
		if(StringUtils.isBlank(bankName)) {
			return "unknown";
		}
		return bankName;
	}
	
}
