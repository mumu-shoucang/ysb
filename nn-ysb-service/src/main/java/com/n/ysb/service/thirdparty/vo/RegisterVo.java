package com.n.ysb.service.thirdparty.vo;

import java.io.File;
import java.util.Date;

import com.n.ysb.service.merchant.vo.NnMerchantVo;

public class RegisterVo extends NnMerchantVo {

    //注册请求号，每次请求唯一
    private String requestId;
    
    //商户类型
    private String customerType;
    
    //营业执照号
    private String businessLicence;
    
    //签约名
    private String signedName;
    
    //推荐人姓名
    private String linkMan;
    
    //身份证号
    private String idCard;
    
    //法人姓名
    private String legalPerson;
    
    //起结金额
    private String minSettleAmount;
    
    //银行卡类型
    private String bankAccountType;
    
    //银行卡正面照
    private File bankCardPhoto;
    
    //个体工商户正面照
    private File businessLicensePhoto;
    
    //身份证正面照
    private File idCardPhoto;
    
    //身份证背面照
    private File idCardBackPhoto;
    
    //身份证+银行卡+本人合照
    private File personPhoto;
    
    //电子协议
    private File electronicAgreement;
    
    //审核状态,SUCCESS/success:审核成功,FAILED/failed:审核失败,空:注册时不审核子商户，
    private String auditStatus;
    
    //身份证号开始时间
    private Date idCardStart;

    //身份证号结束时间
    private Date idCardEnd;
    
    //签名信息
    private String hmac;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getBusinessLicence() {
        return businessLicence;
    }

    public void setBusinessLicence(String businessLicence) {
        this.businessLicence = businessLicence;
    }

    public String getSignedName() {
        return signedName;
    }

    public void setSignedName(String signedName) {
        this.signedName = signedName;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getMinSettleAmount() {
        return minSettleAmount;
    }

    public void setMinSettleAmount(String minSettleAmount) {
        this.minSettleAmount = minSettleAmount;
    }

    public String getBankAccountType() {
        return bankAccountType;
    }

    public void setBankAccountType(String bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    public File getBankCardPhoto() {
        return bankCardPhoto;
    }

    public void setBankCardPhoto(File bankCardPhoto) {
        this.bankCardPhoto = bankCardPhoto;
    }

    public File getBusinessLicensePhoto() {
        return businessLicensePhoto;
    }

    public void setBusinessLicensePhoto(File businessLicensePhoto) {
        this.businessLicensePhoto = businessLicensePhoto;
    }

    public File getIdCardPhoto() {
        return idCardPhoto;
    }

    public void setIdCardPhoto(File idCardPhoto) {
        this.idCardPhoto = idCardPhoto;
    }

    public File getIdCardBackPhoto() {
        return idCardBackPhoto;
    }

    public void setIdCardBackPhoto(File idCardBackPhoto) {
        this.idCardBackPhoto = idCardBackPhoto;
    }

    public File getPersonPhoto() {
        return personPhoto;
    }

    public void setPersonPhoto(File personPhoto) {
        this.personPhoto = personPhoto;
    }

    public File getElectronicAgreement() {
        return electronicAgreement;
    }

    public void setElectronicAgreement(File electronicAgreement) {
        this.electronicAgreement = electronicAgreement;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
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

    public String getHmac() {
        return hmac;
    }

    public void setHmac(String hmac) {
        this.hmac = hmac;
    }
}
