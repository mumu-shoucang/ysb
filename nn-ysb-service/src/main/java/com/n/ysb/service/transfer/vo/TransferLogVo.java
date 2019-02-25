package com.n.ysb.service.transfer.vo;

import java.math.BigDecimal;
import java.util.Date;

public class TransferLogVo {

    private String opDate;

    private String opUser;

    private Date transferDate; // 交易日期

    private String refSign;

    private String yeeCustomerNumber;

    private BigDecimal transferAmt;

    private String transferStatus;

    private String remark;
    
    private String startDate;
    
    private String endDate;


    public String getOpUser() {
        return opUser;
    }

    public void setOpUser(String opUser) {
        this.opUser = opUser == null ? null : opUser.trim();
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    public String getRefSign() {
        return refSign;
    }

    public void setRefSign(String refSign) {
        this.refSign = refSign == null ? null : refSign.trim();
    }

    public String getYeeCustomerNumber() {
        return yeeCustomerNumber;
    }

    public void setYeeCustomerNumber(String yeeCustomerNumber) {
        this.yeeCustomerNumber = yeeCustomerNumber == null ? null : yeeCustomerNumber.trim();
    }

    public BigDecimal getTransferAmt() {
        return transferAmt;
    }

    public void setTransferAmt(BigDecimal transferAmt) {
        this.transferAmt = transferAmt;
    }

    public String getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus == null ? null : transferStatus.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getOpDate() {
		return opDate;
	}

	public void setOpDate(String opDate) {
		this.opDate = opDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
