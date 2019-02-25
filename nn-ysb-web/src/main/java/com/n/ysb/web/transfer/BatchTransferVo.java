package com.n.ysb.web.transfer;

import java.util.List;

import com.n.ysb.service.statistics.po.NnStatistics;

public class BatchTransferVo {

	private List<NnStatistics> statPos;
	private String smsCode;
	
	public List<NnStatistics> getStatPos() {
		return statPos;
	}
	public void setStatPos(List<NnStatistics> statPos) {
		this.statPos = statPos;
	}
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
}
