package com.n.ysb.service.thirdparty;

public enum RiskReserveDay {
    T0("0", "开通 T1、 T0自助结算(推荐)"),
    T1("1", "开通 T1自助结算");
    
    private String code;
    private String desc;
    
    private RiskReserveDay(String code, String desc) {
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
}
