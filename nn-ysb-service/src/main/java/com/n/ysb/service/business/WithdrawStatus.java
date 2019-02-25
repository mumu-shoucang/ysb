package com.n.ysb.service.business;

public enum WithdrawStatus {
    
    init("0", "已创建"), withdrawing("1", "结算中"), withdraw_suc("2","结算成功"), withdraw_fail("3","结算失败");
    
    private String code;
    private String desc;
    private WithdrawStatus(String code, String desc) {
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
        for(WithdrawStatus of : WithdrawStatus.values()){
            if(of.code.equals(code)){
                return of.desc;
            }
        }
        return null;
    }
    
    public static WithdrawStatus getWithdrawStatusByCode(String code){
        for(WithdrawStatus withdrawStatus : WithdrawStatus.values()){
            if(code.equals(withdrawStatus.getCode())){
                return withdrawStatus;
            }
        }
        
        return null;
    }
}
