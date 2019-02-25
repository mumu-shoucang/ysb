package com.n.ysb.service.business.pars;

import org.apache.commons.lang.StringUtils;

import com.n.ysb.service.business.pars.base.AESString;
import com.n.ysb.service.business.pars.base.Sign;
import com.n.ysb.service.util.MD5;


public class GetCommisstionPars extends BaseParsWithToken{
	private String yeeCustomerNumber;
	
    public String getYeeCustomerNumber() {
        return yeeCustomerNumber;
    }

    public void setYeeCustomerNumber(String yeeCustomerNumber) {
        this.yeeCustomerNumber = yeeCustomerNumber;
    }
    
    @Override
    public String sign(String key) {
        String s = MD5.md5(this.getTimestamp() + this.getMerchantMobile() + yeeCustomerNumber + key);
        return s;
    }
    
    @Override
    public boolean hasNull() {
        if(super.hasNull()) {
            return true;
        }
        if(StringUtils.isBlank(this.yeeCustomerNumber)) {
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return super.toString() + "--" + this.yeeCustomerNumber;
    }
}
