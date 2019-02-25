package com.n.ysb.service.order.vo;

import java.math.BigDecimal;
import java.util.Date;

public class NnOrderVo {
    private String id;

    private String orderNo;

    private Date createDate;

    private String merchantMobile;

    private String refSign;

    private String orderStatus;

    private String debitCardNo;

    private String creditCardNo;

    private BigDecimal orderAmt;

    private BigDecimal tradeFee;

    private BigDecimal t0WithdrawFee;

    private String yeeExternalLd;

    private String yeePayStatus;

    private Date yeePayDate;

    private BigDecimal yeeTradeFee;

    private String yeeWithdrawStatus;

    private Date yeeWithdrawHandleDate;

    private String yeeReceiver;

    private String yeeReceiverBankcardNo;

    private BigDecimal yeeWithdrawAmt;

    private BigDecimal yeeWithdrawActualAmt;

    private BigDecimal yeeT0WithdrawFee;

    private BigDecimal yeeT0WithdrawExfee;

    private String mcc;

    private BigDecimal t0WithdrawExfee;

    private BigDecimal commisstion;
    
    private String startTime;
    
    private String endTime;
    
    private BigDecimal tradeCommisstion;
    
    private BigDecimal withdrawCommisstion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getMerchantMobile() {
        return merchantMobile;
    }

    public void setMerchantMobile(String merchantMobile) {
        this.merchantMobile = merchantMobile == null ? null : merchantMobile.trim();
    }

    public String getRefSign() {
        return refSign;
    }

    public void setRefSign(String refSign) {
        this.refSign = refSign == null ? null : refSign.trim();
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public String getDebitCardNo() {
        return debitCardNo;
    }

    public void setDebitCardNo(String debitCardNo) {
        this.debitCardNo = debitCardNo == null ? null : debitCardNo.trim();
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo == null ? null : creditCardNo.trim();
    }

    public BigDecimal getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(BigDecimal orderAmt) {
        this.orderAmt = orderAmt;
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

    public String getYeeExternalLd() {
        return yeeExternalLd;
    }

    public void setYeeExternalLd(String yeeExternalLd) {
        this.yeeExternalLd = yeeExternalLd == null ? null : yeeExternalLd.trim();
    }

    public String getYeePayStatus() {
        return yeePayStatus;
    }

    public void setYeePayStatus(String yeePayStatus) {
        this.yeePayStatus = yeePayStatus == null ? null : yeePayStatus.trim();
    }

    public Date getYeePayDate() {
        return yeePayDate;
    }

    public void setYeePayDate(Date yeePayDate) {
        this.yeePayDate = yeePayDate;
    }

    public BigDecimal getYeeTradeFee() {
        return yeeTradeFee;
    }

    public void setYeeTradeFee(BigDecimal yeeTradeFee) {
        this.yeeTradeFee = yeeTradeFee;
    }

    public String getYeeWithdrawStatus() {
        return yeeWithdrawStatus;
    }

    public void setYeeWithdrawStatus(String yeeWithdrawStatus) {
        this.yeeWithdrawStatus = yeeWithdrawStatus == null ? null : yeeWithdrawStatus.trim();
    }

    public Date getYeeWithdrawHandleDate() {
        return yeeWithdrawHandleDate;
    }

    public void setYeeWithdrawHandleDate(Date yeeWithdrawHandleDate) {
        this.yeeWithdrawHandleDate = yeeWithdrawHandleDate;
    }

    public String getYeeReceiver() {
        return yeeReceiver;
    }

    public void setYeeReceiver(String yeeReceiver) {
        this.yeeReceiver = yeeReceiver == null ? null : yeeReceiver.trim();
    }

    public String getYeeReceiverBankcardNo() {
        return yeeReceiverBankcardNo;
    }

    public void setYeeReceiverBankcardNo(String yeeReceiverBankcardNo) {
        this.yeeReceiverBankcardNo = yeeReceiverBankcardNo == null ? null : yeeReceiverBankcardNo.trim();
    }

    public BigDecimal getYeeWithdrawAmt() {
        return yeeWithdrawAmt;
    }

    public void setYeeWithdrawAmt(BigDecimal yeeWithdrawAmt) {
        this.yeeWithdrawAmt = yeeWithdrawAmt;
    }

    public BigDecimal getYeeWithdrawActualAmt() {
        return yeeWithdrawActualAmt;
    }

    public void setYeeWithdrawActualAmt(BigDecimal yeeWithdrawActualAmt) {
        this.yeeWithdrawActualAmt = yeeWithdrawActualAmt;
    }

    public BigDecimal getYeeT0WithdrawFee() {
        return yeeT0WithdrawFee;
    }

    public void setYeeT0WithdrawFee(BigDecimal yeeT0WithdrawFee) {
        this.yeeT0WithdrawFee = yeeT0WithdrawFee;
    }

    public BigDecimal getYeeT0WithdrawExfee() {
        return yeeT0WithdrawExfee;
    }

    public void setYeeT0WithdrawExfee(BigDecimal yeeT0WithdrawExfee) {
        this.yeeT0WithdrawExfee = yeeT0WithdrawExfee;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc == null ? null : mcc.trim();
    }

    public BigDecimal getT0WithdrawExfee() {
        return t0WithdrawExfee;
    }

    public void setT0WithdrawExfee(BigDecimal t0WithdrawExfee) {
        this.t0WithdrawExfee = t0WithdrawExfee;
    }

    public BigDecimal getCommisstion() {
        return commisstion;
    }

    public void setCommisstion(BigDecimal commisstion) {
        this.commisstion = commisstion;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getTradeCommisstion() {
        return tradeCommisstion;
    }

    public void setTradeCommisstion(BigDecimal tradeCommisstion) {
        this.tradeCommisstion = tradeCommisstion;
    }

    public BigDecimal getWithdrawCommisstion() {
        return withdrawCommisstion;
    }

    public void setWithdrawCommisstion(BigDecimal withdrawCommisstion) {
        this.withdrawCommisstion = withdrawCommisstion;
    }

    @Override
    public String toString() {
        return "NnOrder [id=" + id + ", orderNo=" + orderNo + ", createDate=" + createDate + ", merchantMobile=" + merchantMobile
                + ", refSign=" + refSign + ", orderStatus=" + orderStatus + ", debitCardNo=" + debitCardNo + ", creditCardNo="
                + creditCardNo + ", orderAmt=" + orderAmt + ", tradeFee=" + tradeFee + ", t0WithdrawFee=" + t0WithdrawFee
                + ", yeeExternalLd=" + yeeExternalLd + ", yeePayStatus=" + yeePayStatus + ", yeePayDate=" + yeePayDate
                + ", yeeTradeFee=" + yeeTradeFee + ", yeeWithdrawStatus=" + yeeWithdrawStatus + ", yeeWithdrawHandleDate="
                + yeeWithdrawHandleDate + ", yeeReceiver=" + yeeReceiver + ", yeeReceiverBankcardNo=" + yeeReceiverBankcardNo
                + ", yeeWithdrawAmt=" + yeeWithdrawAmt + ", yeeWithdrawActualAmt=" + yeeWithdrawActualAmt + ", yeeT0WithdrawFee="
                + yeeT0WithdrawFee + ", yeeT0WithdrawExfee=" + yeeT0WithdrawExfee + ", mcc=" + mcc + ", t0WithdrawExfee="
                + t0WithdrawExfee + ", commisstion=" + commisstion + ", startTime=" + startTime + ", endTime=" + endTime
                + ", getId()=" + getId() + ", getOrderNo()=" + getOrderNo() + ", getCreateDate()=" + getCreateDate()
                + ", getMerchantMobile()=" + getMerchantMobile() + ", getRefSign()=" + getRefSign() + ", getOrderStatus()="
                + getOrderStatus() + ", getDebitCardNo()=" + getDebitCardNo() + ", getCreditCardNo()=" + getCreditCardNo()
                + ", getOrderAmt()=" + getOrderAmt() + ", getTradeFee()=" + getTradeFee() + ", getT0WithdrawFee()="
                + getT0WithdrawFee() + ", getYeeExternalLd()=" + getYeeExternalLd() + ", getYeePayStatus()=" + getYeePayStatus()
                + ", getYeePayDate()=" + getYeePayDate() + ", getYeeTradeFee()=" + getYeeTradeFee() + ", getYeeWithdrawStatus()="
                + getYeeWithdrawStatus() + ", getYeeWithdrawHandleDate()=" + getYeeWithdrawHandleDate() + ", getYeeReceiver()="
                + getYeeReceiver() + ", getYeeReceiverBankcardNo()=" + getYeeReceiverBankcardNo() + ", getYeeWithdrawAmt()="
                + getYeeWithdrawAmt() + ", getYeeWithdrawActualAmt()=" + getYeeWithdrawActualAmt() + ", getYeeT0WithdrawFee()="
                + getYeeT0WithdrawFee() + ", getYeeT0WithdrawExfee()=" + getYeeT0WithdrawExfee() + ", getMcc()=" + getMcc()
                + ", getT0WithdrawExfee()=" + getT0WithdrawExfee() + ", getCommisstion()=" + getCommisstion()
                + ", getStartTime()=" + getStartTime() + ", getEndTime()=" + getEndTime() + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }
    
    
}