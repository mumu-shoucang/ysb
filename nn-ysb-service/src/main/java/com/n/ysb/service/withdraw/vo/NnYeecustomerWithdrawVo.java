package com.n.ysb.service.withdraw.vo;

import java.math.BigDecimal;
import java.util.Date;

public class NnYeecustomerWithdrawVo {
    private String id;

    private String yeeCustomerNumber;

    private String orderNo;

    private BigDecimal withdrawAmt;

    private String withdrawStatus;

    private Date createDate;

    private Date yeeWithdrawHandleDate;

    private String yeeReceiver;

    private String yeeReceiverBankcardNo;

    private BigDecimal yeeWithdrawAmt;

    private BigDecimal yeeWithdrawActualAmt;

    private BigDecimal yeeT0WithdrawFee;

    private BigDecimal yeeT0WithdrawExfee;

    private String yeeExternalLd;

    private String yeeWithdrawStatus;
    
    private Date startDate;
    
    private Date endDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getYeeCustomerNumber() {
        return yeeCustomerNumber;
    }

    public void setYeeCustomerNumber(String yeeCustomerNumber) {
        this.yeeCustomerNumber = yeeCustomerNumber == null ? null : yeeCustomerNumber.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public BigDecimal getWithdrawAmt() {
        return withdrawAmt;
    }

    public void setWithdrawAmt(BigDecimal withdrawAmt) {
        this.withdrawAmt = withdrawAmt;
    }

    public String getWithdrawStatus() {
        return withdrawStatus;
    }

    public void setWithdrawStatus(String withdrawStatus) {
        this.withdrawStatus = withdrawStatus == null ? null : withdrawStatus.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public String getYeeExternalLd() {
        return yeeExternalLd;
    }

    public void setYeeExternalLd(String yeeExternalLd) {
        this.yeeExternalLd = yeeExternalLd == null ? null : yeeExternalLd.trim();
    }

    public String getYeeWithdrawStatus() {
        return yeeWithdrawStatus;
    }

    public void setYeeWithdrawStatus(String yeeWithdrawStatus) {
        this.yeeWithdrawStatus = yeeWithdrawStatus == null ? null : yeeWithdrawStatus.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}