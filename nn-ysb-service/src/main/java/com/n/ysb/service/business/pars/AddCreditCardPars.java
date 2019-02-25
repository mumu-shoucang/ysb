package com.n.ysb.service.business.pars;

import org.apache.commons.lang.StringUtils;

import com.n.ysb.service.business.pars.base.AESString;
import com.n.ysb.service.util.MD5;

public class AddCreditCardPars extends BaseParsWithToken {
	
	private String cardNo;
	private AESString bindMobile;
	private String smsCode;
	
	private String bankCode;
	private String bankName;
	
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
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
		return MD5.md5(this.getTimestamp() + this.getMerchantMobile() + this.cardNo + key);
	}
	
	@Override
	public boolean hasNull() {
		if(super.hasNull()) {
			return true;
		}
		if(StringUtils.isBlank(this.cardNo) || this.bindMobile == null || StringUtils.isBlank(this.smsCode)) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		String ss = super.toString();
		return ss + "[" + this.cardNo + "-" + this.bindMobile + "-" + this.smsCode + "]";
	}
	
}
