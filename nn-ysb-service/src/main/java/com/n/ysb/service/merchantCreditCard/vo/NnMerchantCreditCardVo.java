package com.n.ysb.service.merchantCreditCard.vo;

public class NnMerchantCreditCardVo {

    private String cardCode;
    private String cardNo;
    private String bankCode;
    private String bankName;
    private String showTag;

    public String getCardCode() {
        return cardCode;
    }
    public void setCardCode(String cardCode) {
        this.cardCode = cardCode == null ? null : cardCode.trim();
    }
    public String getCardNo() {
        return cardNo;
    }
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getShowTag() {
		return showTag;
	}
	public void setShowTag(String showTag) {
		this.showTag = showTag;
	}
    
}