package com.n.ysb.service.business.pars;

import java.math.BigDecimal;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;

import com.n.ysb.service.util.MD5;

public class ReceivePars extends BaseParsWithToken {

	// pars
    private String creditCardCode;
    private BigDecimal orderAmt;
    private String mcc;
    
    
    // computed fields
    private BigDecimal tradeFee;
    private BigDecimal t0WithdrawFee;
    private BigDecimal t0WithdrawEXFee;
    
    
	public BigDecimal getOrderAmt() {
		return orderAmt;
	}
	public void setOrderAmt(BigDecimal orderAmt) {
		this.orderAmt = orderAmt;
	}
	public String getCreditCardCode() {
		return creditCardCode == null ? "" : creditCardCode;
	}
	public void setCreditCardCode(String creditCardCode) {
		this.creditCardCode = creditCardCode == null ? "" : creditCardCode;
	}
	public BigDecimal getTradeFee() {
		return tradeFee;
	}
	public void setTradeFee(BigDecimal tradeFee) {
		this.tradeFee = tradeFee;
	}
	public BigDecimal getT0WithdrawFee() {
		return t0WithdrawFee;
	}
	public void setT0WithdrawFee(BigDecimal t0WithdrawFee) {
		this.t0WithdrawFee = t0WithdrawFee;
	}
	public BigDecimal getT0WithdrawEXFee() {
		return t0WithdrawEXFee;
	}
	public void setT0WithdrawEXFee(BigDecimal t0WithdrawEXFee) {
		this.t0WithdrawEXFee = t0WithdrawEXFee;
	}
	public String getMcc() {
		return mcc;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	
	@Override
	public String sign(String key) {
		String s = MD5.md5(this.getTimestamp() + this.getMerchantMobile() + this.getCreditCardCode() + orderAmt + key);
		return s;
	}
	
	@Override
	public String toString() {
		String ss = super.toString();
		return ss + "["+this.creditCardCode + "-" + this.mcc + "-" + this.orderAmt +"]";
	}
	
	@Override
	public boolean hasNull() {
		if(super.hasNull()) {
			return true;
		}
		if(StringUtils.isBlank(this.creditCardCode) || StringUtils.isBlank(this.mcc) || StringUtils.isBlank(Objects.toString(this.orderAmt, "")) || this.orderAmt.compareTo(new BigDecimal(0)) < 1) {
			return true;
		}
		return false;
	}
	
}
