package com.n.ysb.service.thirdparty;

public enum ManualSettle {
    YES("Y", "自助结算"),
    NO("N", "隔天自动打款");
    
    private String code;
    private String desc;
    
    private ManualSettle(String code, String desc) {
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
