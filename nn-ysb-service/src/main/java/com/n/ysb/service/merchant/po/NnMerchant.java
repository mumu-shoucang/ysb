package com.n.ysb.service.merchant.po;

import java.util.Date;

public class NnMerchant {
    private String id;

    private String merchantCode;

    private String loginMobile;

    private String loginPwd;

    private String refSign;

    private String bindMobile;

    private String bankAccountName;

    private String bankAccountNo;

    private String bankName;

    private String idCard;

    private Date idCardStart;

    private Date idCardEnd;

    private String feeSetFlag;

    private String feeSetType;

    private Date createDate;
    
    private String yeeCustomerNumber;
    
    private Date authDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

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

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd == null ? null : loginPwd.trim();
    }

    public String getRefSign() {
        return refSign;
    }

    public void setRefSign(String refSign) {
        this.refSign = refSign == null ? null : refSign.trim();
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

    public String getFeeSetFlag() {
        return feeSetFlag;
    }

    public void setFeeSetFlag(String feeSetFlag) {
        this.feeSetFlag = feeSetFlag == null ? null : feeSetFlag.trim();
    }

    public String getFeeSetType() {
        return feeSetType;
    }
    
    public void setFeeSetType(String feeSetType) {
        this.feeSetType = feeSetType == null ? null : feeSetType.trim();
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
		this.yeeCustomerNumber = yeeCustomerNumber == null ? null : yeeCustomerNumber.trim();
	}

    public Date getAuthDate() {
        return authDate;
    }

    public void setAuthDate(Date authDate) {
        this.authDate = authDate;
    }
    
}