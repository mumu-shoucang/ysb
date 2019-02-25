package com.n.ysb.service.thirdparty;


public enum WithdrawType {

    order_hand_withdraw("1", "订单手动结算"),merchant_withdraw("2", "子商户结算");
    
    private String code;
    private String desc;
    
    private WithdrawType(String code, String desc) {
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
    
    public static String getNameByCode(String code){
		for(WithdrawType of : WithdrawType.values()){
			if(of.code.equals(code)){
				return of.name();
			}
		}
		return null;
	}
    
    public static void main(String[] args) {
    	String nameByCode = WithdrawType.getNameByCode("1");
    	System.out.println(nameByCode);
	}
}
