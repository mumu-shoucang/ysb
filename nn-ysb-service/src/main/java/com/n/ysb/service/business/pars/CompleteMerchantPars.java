package com.n.ysb.service.business.pars;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.n.ysb.service.business.pars.base.AESString;
import com.n.ysb.service.util.MD5;


public class CompleteMerchantPars extends BaseParsWithToken {

    private String bankAccountName;
    private String bankAccountNo;
	private String idCard;
	private Date idCardStart;
    private Date idCardEnd;
	private AESString bindMobile; // 商户注册的手机号，不一定 是银行预留手机号
    
    
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
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Date getIdCardStart() {
		return idCardStart;
	}
	public void setIdCardStart(Date idCardStart) {
		this.idCardStart = idCardStart;
	}
	public Date getIdCardEnd() {
		return idCardEnd;
	}
	public void setIdCardEnd(Date idCardEnd) {
		this.idCardEnd = idCardEnd;
	}
	public AESString getBindMobile() {
		return bindMobile;
	}
	public void setBindMobile(AESString bindMobile) {
		this.bindMobile = bindMobile;
	}
	
	@Override
	public String sign(String key) {
		return MD5.md5(this.getTimestamp() + this.getMerchantMobile() + this.bankAccountNo + this.idCard + key);
	}
	
	@Override
	public boolean hasNull() {
		if(super.hasNull()) {
			return true;
		}
		if(StringUtils.isBlank(this.bankAccountName) || StringUtils.isBlank(this.bankAccountNo) || StringUtils.isBlank(this.idCard) || 
				this.idCardEnd == null || this.idCardStart == null) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		String ss = super.toString();
		return ss + "["+this.bankAccountName + "-" + this.bankAccountNo + "-" + this.idCard +"]";
	}
	
}
