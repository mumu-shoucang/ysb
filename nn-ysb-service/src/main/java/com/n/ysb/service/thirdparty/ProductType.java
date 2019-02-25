package com.n.ysb.service.thirdparty;


public enum ProductType {

    trade_fee("1", "交易费率"),
    t0_withdraw_fee("3", "T0自助结算基本费率"),
    t0_withdraw_workday_fee("4", "T0自助结算工作日额外费率"),
    t0_withdraw_nonworkday_fee("5", "T0自助结算非工作日额外费率");
    
    private String code;
    private String desc;
    
    private ProductType(String code, String desc) {
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
		for(ProductType of : ProductType.values()){
			if(of.code.equals(code)){
				return of.name();
			}
		}
		return null;
	}
    
    public static void main(String[] args) {
    	String nameByCode = ProductType.getNameByCode("1");
    	System.out.println(nameByCode);
	}
}
