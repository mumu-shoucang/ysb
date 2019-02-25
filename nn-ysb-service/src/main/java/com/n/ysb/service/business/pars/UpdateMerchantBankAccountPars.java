package com.n.ysb.service.business.pars;

import org.apache.commons.lang.StringUtils;

import com.n.ysb.service.business.pars.base.AESString;
import com.n.ysb.service.util.MD5;

public class UpdateMerchantBankAccountPars extends BaseParsWithToken {

    private String bankAccountName;
    private String bankAccountNo;
    private AESString bindMobile;
	private String smsCode;
    
	public String getBankAccountName() {
		return bankAccountName;
	}
	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}
	public String getBankAccountNo() {
		return bankAccountNo;
	}
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	public AESString getBindMobile() {
		return bindMobile;
	}
	public void setBindMobile(AESString bindMobile) {
		this.bindMobile = bindMobile;
	}
	
	@Override
	public String sign(String key) {
		return MD5.md5(this.getTimestamp() + this.getMerchantMobile() + this.bankAccountNo + key);
	}
	
	@Override
	public boolean hasNull() {
		if(super.hasNull()) {
			return true;
		}
		if(StringUtils.isBlank(this.bankAccountName) || StringUtils.isBlank(bankAccountNo) || this.bindMobile == null || StringUtils.isBlank(this.smsCode)) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		String ss = super.toString();
		return ss + "[" + this.bankAccountName + "-" + this.bankAccountNo + "-" + this.bindMobile + "-" + this.smsCode+"]";
	}
	
}
