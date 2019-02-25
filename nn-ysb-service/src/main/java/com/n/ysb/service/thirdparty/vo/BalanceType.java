package com.n.ysb.service.thirdparty.vo;

public enum BalanceType {

	// 可用余额类型: 1:T0 自助结算可用余额 2:T1 自助结算可用余额 3:账户余额
	
	T0_withdraw("1", "T0自助结算可用余额"), T1_withdraw("2", "T1自助结算可用余额"), balance("3", "账户余额");
	
	private String code, desc;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	private BalanceType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static BalanceType getValueByCode(String code) {
		for(BalanceType bt : BalanceType.values()) {
			if(bt.getCode().equals(code)) {
				return bt;
			}
		}
		return null;
	}
}
