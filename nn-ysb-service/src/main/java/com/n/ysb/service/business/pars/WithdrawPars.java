package com.n.ysb.service.business.pars;

import java.math.BigDecimal;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;

import com.n.ysb.service.business.pars.base.AESString;
import com.n.ysb.service.business.pars.base.Sign;
import com.n.ysb.service.util.MD5;


public class WithdrawPars extends BaseParsWithToken{
	private String yeeCustomerNumber;
	
	private BigDecimal withdrawAmt;
	
    public String getYeeCustomerNumber() {
        return yeeCustomerNumber;
    }

    public void setYeeCustomerNumber(String yeeCustomerNumber) {
        this.yeeCustomerNumber = yeeCustomerNumber;
    }
    
    public BigDecimal getWithdrawAmt() {
        return withdrawAmt;
    }

    public void setWithdrawAmt(BigDecimal withdrawAmt) {
        this.withdrawAmt = withdrawAmt;
    }

    @Override
    public String sign(String key) {
        String s = MD5.md5(this.getTimestamp() + this.getMerchantMobile() + yeeCustomerNumber + withdrawAmt + key);
        return s;
    }
    
    @Override
    public boolean hasNull() {
        if(super.hasNull()) {
            return true;
        }
        if(StringUtils.isBlank(this.yeeCustomerNumber) || StringUtils.isBlank(Objects.toString(this.withdrawAmt, "")) || this.withdrawAmt.compareTo(new BigDecimal(0)) < 1) {
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return super.toString() + "--"+this.yeeCustomerNumber + "--" + this.withdrawAmt;
    }
}
