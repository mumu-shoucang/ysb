package com.n.ysb.service.thirdparty.vo;

import java.math.BigDecimal;

import com.n.ysb.service.order.po.NnOrder;

public class ReceiveVo {
	
	private NnOrder order;
	
	public ReceiveVo(NnOrder order) {
		this.order = order;
	}
	
	/** set by sign */
    private String callBackUrl;
    private String webCallBackUrl;
    private String withdrawCallBackUrl;
    /** set by sign */

    
    public String getOrderNo() {
    	return this.order.getOrderNo();
    }
    
    public String getCreditCardNo() {
    	return this.order.getCreditCardNo();
    }
    
    public String getMcc() {
    	return this.order.getMcc();
    }
    
    public BigDecimal getOrderAmt() {
    	return this.order.getOrderAmt();
    }
    
    public String getCallBackUrl() {
        return callBackUrl;
    }

    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
    }

    public String getWebCallBackUrl() {
        return webCallBackUrl;
    }

    public void setWebCallBackUrl(String webCallBackUrl) {
        this.webCallBackUrl = webCallBackUrl;
    }

    public String getWithdrawCallBackUrl() {
        return withdrawCallBackUrl;
    }

    public void setWithdrawCallBackUrl(String withdrawCallBackUrl) {
        this.withdrawCallBackUrl = withdrawCallBackUrl;
    }
    
    
}
