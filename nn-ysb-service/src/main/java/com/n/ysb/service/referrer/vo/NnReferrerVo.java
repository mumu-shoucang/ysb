package com.n.ysb.service.referrer.vo;

import java.math.BigDecimal;
import java.util.Date;

public class NnReferrerVo {
    private String id;

    private String name;

    private String mobile;

    private String refSign;

    private String refCode;

    private String refLink;

    private String refStatus;

    private BigDecimal commissionRate;

    private Date createDate;
    
    private String startTime;
    
    private String endTime;
    
    private BigDecimal basicCommission;
    
    private String idCard;
    
    private String ckBankCard;
   
    public BigDecimal getBasicCommission() {
		return basicCommission;
	}

	public void setBasicCommission(BigDecimal basicCommission) {
		this.basicCommission = basicCommission == null ? null : basicCommission;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard == null ? null : idCard.trim();
	}

	public String getCkBankCard() {
		return ckBankCard;
	}

	public void setCkBankCard(String ckBankCard) {
		this.ckBankCard = ckBankCard == null ? null : ckBankCard;
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

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getRefSign() {
        return refSign;
    }

    public void setRefSign(String refSign) {
        this.refSign = refSign == null ? null : refSign.trim();
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode == null ? null : refCode.trim();
    }

    public String getRefLink() {
        return refLink;
    }

    public void setRefLink(String refLink) {
        this.refLink = refLink == null ? null : refLink.trim();
    }

    public String getRefStatus() {
        return refStatus;
    }

    public void setRefStatus(String refStatus) {
        this.refStatus = refStatus == null ? null : refStatus.trim();
    }

    public BigDecimal getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(BigDecimal commissionRate) {
        this.commissionRate = commissionRate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}