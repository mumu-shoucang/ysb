package com.n.ysb.service.business.pars;

import java.math.BigDecimal;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;

import com.n.ysb.service.util.MD5;

public class ComputeFeePars extends BaseParsWithToken {

	private BigDecimal orderAmt;

	public BigDecimal getOrderAmt() {
		return orderAmt;
	}
	public void setOrderAmt(BigDecimal orderAmt) {
		this.orderAmt = orderAmt;
	}

	@Override
	public String sign(String key) {
		String s = MD5.md5(this.getTimestamp() + this.getMerchantMobile() + orderAmt + key);
		return s;
	}
	
	@Override
	public boolean hasNull() {
		if(super.hasNull()) {
			return true;
		}
		if(StringUtils.isBlank(Objects.toString(this.orderAmt, "")) || this.orderAmt.compareTo(new BigDecimal(0)) < 1) {
			return true;
		}
		return false;
	}

}
