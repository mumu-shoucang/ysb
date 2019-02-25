package com.n.ysb.service.merchant;

public enum FeeSetFlag {
    
    FEE_SET_NONE("0", "费率未设置"),
    FEE_SET_ING("1", "费率设置中"),
    FEE_SET_SUCCESS("2", "费率设置成功"),
    FEE_SET_FAIL("3", "费率设置失败");
    
    private String code;
    private String desc;
    
    private FeeSetFlag(String code, String desc) {
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
