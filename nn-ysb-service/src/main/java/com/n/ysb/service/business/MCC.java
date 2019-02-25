package com.n.ysb.service.business;

public enum MCC {

	store("5311", "百货商店"), flyair("4511", "航空公司"), view("4733", "大型景区售票");
	
	private String code;
	private String desc;
	private MCC(String code, String desc) {
		this.setCode(code);
		this.setDesc(desc);
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public static boolean contains(String code){
		for(MCC mcc : MCC.values()){
			if(mcc.code.equals(code)){
				return true;
			}
		}
		return false;
	}
	
}
