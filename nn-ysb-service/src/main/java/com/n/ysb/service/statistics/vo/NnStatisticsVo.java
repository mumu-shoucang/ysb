package com.n.ysb.service.statistics.vo;

import java.math.BigDecimal;
import java.util.Date;

public class NnStatisticsVo {
    private String id;

    private String statisticsType;

    private Date statisticsDate;

    private BigDecimal statisticsAmt;
    
    private Long tradeSucCount;

    private Long regUserCount;

    private Long antuUserCount;

    private BigDecimal tradeCommisstionRate;

    private BigDecimal tradeCommisstion;

    private BigDecimal withdrawCommisstion;
    
    private Date startDate;
    
    private Date endDate;
    
    private String commissionTransferStat; // 佣金结算状态
    private String exRefSign; // 不包括的推举人标识

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getStatisticsType() {
        return statisticsType;
    }

    public void setStatisticsType(String statisticsType) {
        this.statisticsType = statisticsType == null ? null : statisticsType.trim();
    }

    public Date getStatisticsDate() {
        return statisticsDate;
    }

    public void setStatisticsDate(Date statisticsDate) {
        this.statisticsDate = statisticsDate;
    }

    public BigDecimal getStatisticsAmt() {
        return statisticsAmt;
    }

    public void setStatisticsAmt(BigDecimal statisticsAmt) {
        this.statisticsAmt = statisticsAmt;
    }

    public Long getTradeSucCount() {
        return tradeSucCount;
    }

    public void setTradeSucCount(Long tradeSucCount) {
        this.tradeSucCount = tradeSucCount;
    }

    public Long getRegUserCount() {
        return regUserCount;
    }

    public void setRegUserCount(Long regUserCount) {
        this.regUserCount = regUserCount;
    }

    public Long getAntuUserCount() {
        return antuUserCount;
    }

    public void setAntuUserCount(Long antuUserCount) {
        this.antuUserCount = antuUserCount;
    }

    public BigDecimal getTradeCommisstionRate() {
        return tradeCommisstionRate;
    }

    public void setTradeCommisstionRate(BigDecimal tradeCommisstionRate) {
        this.tradeCommisstionRate = tradeCommisstionRate;
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

	public String getCommissionTransferStat() {
		return commissionTransferStat;
	}

	public void setCommissionTransferStat(String commissionTransferStat) {
		this.commissionTransferStat = commissionTransferStat;
	}

	public String getExRefSign() {
		return exRefSign;
	}

	public void setExRefSign(String exRefSign) {
		this.exRefSign = exRefSign;
	}
	
}