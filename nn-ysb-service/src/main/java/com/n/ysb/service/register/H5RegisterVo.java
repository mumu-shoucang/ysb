package com.n.ysb.service.register;

import com.n.ysb.service.business.pars.base.AESString;

public class H5RegisterVo {

    private String refCode;
    
    private AESString merchantMobile;
    
    private String pwd;
    
    private String smsCode;

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }


    public AESString getMerchantMobile() {
        return merchantMobile;
    }

    public void setMerchantMobile(AESString merchantMobile) {
        this.merchantMobile = merchantMobile;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
    
}
