package com.n.ysb.service.thirdparty;

public enum CustomerType {
    ENTERPRISE("ENTERPRISE", "企业"),
    INDIVIDUAL("INDIVIDUAL", "个体工商户"),
    PERSON("PERSON", "个人");
    
    private String code;
    private String desc;
    
    private CustomerType(String code, String desc) {
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
