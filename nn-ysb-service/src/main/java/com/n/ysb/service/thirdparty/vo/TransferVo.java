package com.n.ysb.service.thirdparty.vo;


public class TransferVo {

	private String requestId, customerNumber, transAmount;
	private String remark;
	
	public TransferVo(String requestId, String customerNumber, String transAmount) {
		this.requestId = requestId;
		this.customerNumber = customerNumber;
		this.transAmount = transAmount;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(String transAmount) {
		this.transAmount = transAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
