package com.n.ysb.service.business.callbackin.vo;

public class WithdrawBackVo {

	private String mainCustomerNumber; // 系统商编号回传
	private String customerNumber; // 子商户编号回传
	private String externalNo; // 结算请求号 - 收款请求号
	private String serialNo; // 收款宝流水号
	private String transferStatus; // 结算状态: RECEIVED:已接受 PROCESSING:处理中 SUCCESSED:打款成功 FAILED:打款失败 REFUNED:已退款 CANCELLED:已撤销
	private String requestTime; // 请求时间
	private String handleTime; // 处理时间
	private String transferWay; // 结算方式: 1:T0 自助结算 2:T1 自助结算
	private String receiver; // 收款人
	private String receiverBankCardNo; //收款卡号
	private String receiverBank; // 收款银行
	private String amount; // 结算金额
	private String fee; // 手续费-T1 自助结算手续费
	private String basicFee; // 基本手续费-T0 自助结算基本手续费
	private String exTargetFee; // 额外手续费-T0 自助结算额外手续费
	private String actualAmount; // 结算实际到账金额
	private String failReason; // 结算失败的原因
	private String hmac;
	
	@Override
	public String toString() {
		return "mainCustomerNumber-["+this.mainCustomerNumber + "], customerNumber-[" + this.customerNumber + "],"
				+ "externalNo-[" + this.externalNo + "], serialNo-[" + this.serialNo + "],"
				+ "transferStatus-[" + this.transferStatus + "], requestTime-[" + this.requestTime + "],"
				+ "handleTime-[" + this.handleTime + "], transferWay-[" + this.transferWay + "],"
				+ "receiver-[" + this.receiver + "], receiverBankCardNo-[" + this.receiverBankCardNo + "], "
				+ "receiverBank-[" + this.receiverBank + "], amount-[" + this.amount + "], "
				+ "fee-[" + this.fee + "], basicFee-[" + this.basicFee + "], "
				+ "exTargetFee-[" + this.exTargetFee + "], actualAmount-[" + this.actualAmount + "], "
				+ "failReason-[" + this.failReason + "].";
	}
	
	public String getMainCustomerNumber() {
		return mainCustomerNumber;
	}
	public void setMainCustomerNumber(String mainCustomerNumber) {
		this.mainCustomerNumber = mainCustomerNumber;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getExternalNo() {
		return externalNo;
	}
	public void setExternalNo(String externalNo) {
		this.externalNo = externalNo;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getTransferStatus() {
		return transferStatus;
	}
	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public String getHandleTime() {
		return handleTime;
	}
	public void setHandleTime(String handleTime) {
		this.handleTime = handleTime;
	}
	public String getTransferWay() {
		return transferWay;
	}
	public void setTransferWay(String transferWay) {
		this.transferWay = transferWay;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getReceiverBankCardNo() {
		return receiverBankCardNo;
	}
	public void setReceiverBankCardNo(String receiverBankCardNo) {
		this.receiverBankCardNo = receiverBankCardNo;
	}
	public String getReceiverBank() {
		return receiverBank;
	}
	public void setReceiverBank(String receiverBank) {
		this.receiverBank = receiverBank;
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
	public String getBasicFee() {
		return basicFee;
	}
	public void setBasicFee(String basicFee) {
		this.basicFee = basicFee;
	}
	public String getExTargetFee() {
		return exTargetFee;
	}
	public void setExTargetFee(String exTargetFee) {
		this.exTargetFee = exTargetFee;
	}
	public String getActualAmount() {
		return actualAmount;
	}
	public void setActualAmount(String actualAmount) {
		this.actualAmount = actualAmount;
	}
	public String getFailReason() {
		return failReason;
	}
	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	public String getHmac() {
		return hmac;
	}
	public void setHmac(String hmac) {
		this.hmac = hmac;
	}
	
}
