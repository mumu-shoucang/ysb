package com.n.ysb.service.thirdparty;

public enum TransferWay {
    T0("1", "T0 自助结算"),
    T1("2", "T1 自助结算");
    
    private String code;
    private String desc;
    
    private TransferWay(String code, String desc) {
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
