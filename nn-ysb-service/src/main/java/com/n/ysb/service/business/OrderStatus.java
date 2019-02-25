package com.n.ysb.service.business;

public enum OrderStatus {

	init("0", "已创建"), paying("1", "支付中"), pay_suc("2","支付成功"), withdraw_suc("3", "结算成功"), trade_closed("4", "交易关闭");
	
	private String code;
	private String desc;
	private OrderStatus(String code, String desc) {
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
	
	public static String getDescByCode(String code){
		for(OrderStatus of : OrderStatus.values()){
			if(of.code.equals(code)){
				return of.desc;
			}
		}
		return null;
	}
	
	public static OrderStatus getOrderStatusByCode(String code){
	    for(OrderStatus orderStatus : OrderStatus.values()){
	        if(code.equals(orderStatus.getCode())){
	            return orderStatus;
	        }
	    }
	    
	    return null;
	}
}
