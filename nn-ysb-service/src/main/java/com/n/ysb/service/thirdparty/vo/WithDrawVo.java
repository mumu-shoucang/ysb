package com.n.ysb.service.thirdparty.vo;

import java.math.BigDecimal;

import com.n.ysb.service.order.vo.NnOrderVo;

public class WithDrawVo extends NnOrderVo {
    private String externalNo;
    
    private String transferWay;
    
    private String callBackUrl;
    
    private BigDecimal withDrawAmt;

    public String getExternalNo() {
        return externalNo;
    }

    public void setExternalNo(String externalNo) {
        this.externalNo = externalNo;
    }

    public String getTransferWay() {
        return transferWay;
    }

    public void setTransferWay(String transferWay) {
        this.transferWay = transferWay;
    }

    public String getCallBackUrl() {
        return callBackUrl;
    }

    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
    }

    public BigDecimal getWithDrawAmt() {
        return withDrawAmt;
    }

    public void setWithDrawAmt(BigDecimal withDrawAmt) {
        this.withDrawAmt = withDrawAmt;
    }

}
