package com.n.ysb.service.globalCfg.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.n.ysb.service.globalCfg.validator.GlobalCfgValid;

@GlobalCfgValid
public class NnGlobalCfgVo{
    
    private String id;

    private Long singleLimit;

    private Long dayLimit;

    private Long monthLimit;

    private Long dayCount;

    private Long monthCount;

    private BigDecimal tradeFee;

    private BigDecimal t1WithdrawFee;

    private BigDecimal t0WithdrawFee;

    private BigDecimal t0WithdrawWorkdayFee;

    private BigDecimal t0WithdrawNonworkdayFee;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Long getSingleLimit() {
        return singleLimit;
    }

    public void setSingleLimit(Long singleLimit) {
        this.singleLimit = singleLimit;
    }

    public Long getDayLimit() {
        return dayLimit;
    }

    public void setDayLimit(Long dayLimit) {
        this.dayLimit = dayLimit;
    }

    public Long getMonthLimit() {
        return monthLimit;
    }

    public void setMonthLimit(Long monthLimit) {
        this.monthLimit = monthLimit;
    }

    public Long getDayCount() {
        return dayCount;
    }

    public void setDayCount(Long dayCount) {
        this.dayCount = dayCount;
    }

    public Long getMonthCount() {
        return monthCount;
    }

    public void setMonthCount(Long monthCount) {
        this.monthCount = monthCount;
    }

    public BigDecimal getTradeFee() {
        return tradeFee;
    }

    public void setTradeFee(BigDecimal tradeFee) {
        this.tradeFee = tradeFee;
    }

    public BigDecimal getT1WithdrawFee() {
        return t1WithdrawFee;
    }

    public void setT1WithdrawFee(BigDecimal t1WithdrawFee) {
        this.t1WithdrawFee = t1WithdrawFee;
    }

    public BigDecimal getT0WithdrawFee() {
        return t0WithdrawFee;
    }

    public void setT0WithdrawFee(BigDecimal t0WithdrawFee) {
        this.t0WithdrawFee = t0WithdrawFee;
    }

    public BigDecimal getT0WithdrawWorkdayFee() {
        return t0WithdrawWorkdayFee;
    }

    public void setT0WithdrawWorkdayFee(BigDecimal t0WithdrawWorkdayFee) {
        this.t0WithdrawWorkdayFee = t0WithdrawWorkdayFee;
    }

    public BigDecimal getT0WithdrawNonworkdayFee() {
        return t0WithdrawNonworkdayFee;
    }

    public void setT0WithdrawNonworkdayFee(BigDecimal t0WithdrawNonworkdayFee) {
        this.t0WithdrawNonworkdayFee = t0WithdrawNonworkdayFee;
    }

    @Override
    public String toString() {
        return "NnGlobalCfgVo [id=" + id + ", singleLimit=" + singleLimit + ", dayLimit=" + dayLimit + ", monthLimit="
                + monthLimit + ", dayCount=" + dayCount + ", monthCount=" + monthCount + ", tradeFee=" + tradeFee
                + ", t1WithdrawFee=" + t1WithdrawFee + ", t0WithdrawFee=" + t0WithdrawFee + ", t0WithdrawWorkdayFee="
                + t0WithdrawWorkdayFee + ", t0WithdrawNonworkdayFee=" + t0WithdrawNonworkdayFee + "]";
    }
}