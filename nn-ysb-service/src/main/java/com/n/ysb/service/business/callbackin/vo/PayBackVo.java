package com.n.ysb.service.business.callbackin.vo;

public class PayBackVo {

	private String code;
	private String message;
	private String requestId; // 收款订单号回传
	private String customerNumber; // 子商户编号回传
	private String externalld; // 收款宝交易流水号
	private String payTime;
	private String amount;
	private String fee;
	private String status; // 订单状态: INIT:未支付 SUCCESS:成功 FAIL:失败 FROZEN:冻结 THAWED:解冻 REVERSE:冲正
	private String busiType;
	private String bankCode; // 银行编码
	private String payerName; // 持卡人姓名
	private String payerPhone; // 银行预留手机号
	private String lastNo; // 银行卡后四位
	private String src; // 支付方式，传英文字母: D:卡号收款 B:店主代付 S:短信收款 T:二维码收款
	private String hmac;
	
	@Override
	public String toString() {
		return "code-["+this.code + "], message-[" + this.message + "],"
				+ "requestId-[" + this.requestId + "], customerNumber-[" + this.customerNumber + "],"
				+ "externalld-[" + this.externalld + "], payTime-[" + this.payTime + "],"
				+ "amount-[" + this.amount + "], fee-[" + this.fee + "],"
				+ "status-[" + this.status + "], busiType-[" + this.busiType + "], "
				+ "bankCode-[" + this.bankCode + "], payerName-[" + this.payerName + "], "
				+ "payerPhone-[" + this.payerPhone + "], lastNo-[" + this.lastNo + "], "
				+ "src-[" + this.src + "].";
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	public String getExternalld() {
		return externalld;
	}
	public void setExternalld(String externalld) {
		this.externalld = externalld;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBusiType() {
		return busiType;
	}
	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getPayerName() {
		return payerName;
	}
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}
	public String getPayerPhone() {
		return payerPhone;
	}
	public void setPayerPhone(String payerPhone) {
		this.payerPhone = payerPhone;
	}
	public String getLastNo() {
		return lastNo;
	}
	public void setLastNo(String lastNo) {
		this.lastNo = lastNo;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getHmac() {
		return hmac;
	}
	public void setHmac(String hmac) {
		this.hmac = hmac;
	}
	
}
