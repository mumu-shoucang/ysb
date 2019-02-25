package com.n.ysb.service.statistics;

public enum StatisticsType {
    
    MainCustomer("1", "系统商费率"),
    Commisstion("2", "推荐人佣金");
    
    private String code;
    private String desc;
    
    private StatisticsType(String code, String desc) {
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
