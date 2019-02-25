package com.n.ysb.service.thirdparty;

/**
 * 是否逐笔结算
 * @author NewNet
 *
 */
public enum AutoWithdraw {
    TRUE("true", "企业"),
    FALSE("false", "个体工商户");
    
    private String code;
    private String desc;
    
    private AutoWithdraw(String code, String desc) {
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
