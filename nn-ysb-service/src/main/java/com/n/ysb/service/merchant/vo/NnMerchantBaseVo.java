package com.n.ysb.service.merchant.vo;

import java.util.Date;

/**
 * http接口使用，用于返回给app的vo 
 * 请注意！！！如果需要新加字段，但不需要返回给app，请加到相应的vo即可，不需要在本类中增加字段！！！
 */
public class NnMerchantBaseVo {
	  
    private String merchantCode;

	private String loginMobile;

	private String bindMobile;

	private String bankAccountName;

	private String bankAccountNo;

	private String bankName;

	private String idCard;

	private Date idCardStart;

	private Date idCardEnd;
	
    private Date createDate;
    
    private String yeeCustomerNumber;
    
    private Date authDate;
    
    private boolean isReferrer;
    
    
    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode == null ? null : merchantCode.trim();
    }

    public String getLoginMobile() {
        return loginMobile;
    }

    public void setLoginMobile(String loginMobile) {
        this.loginMobile = loginMobile == null ? null : loginMobile.trim();
    }

    public String getBindMobile() {
        return bindMobile;
    }

    public void setBindMobile(String bindMobile) {
        this.bindMobile = bindMobile == null ? null : bindMobile.trim();
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName == null ? null : bankAccountName.trim();
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo == null ? null : bankAccountNo.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	public String getYeeCustomerNumber() {
		return yeeCustomerNumber;
	}

	public void setYeeCustomerNumber(String yeeCustomerNumber) {
		this.yeeCustomerNumber = yeeCustomerNumber;
	}

    public Date getAuthDate() {
        return authDate;
    }

    public void setAuthDate(Date authDate) {
        this.authDate = authDate;
    }

    public boolean getIsReferrer() {
        return isReferrer;
    }

    public void setIsReferrer(boolean isReferrer) {
        this.isReferrer = isReferrer;
    }
}
