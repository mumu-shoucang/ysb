package com.n.ysb.service.business;

public class StatPerMobile {

	private Long dayCount;
	private Long dayLimit;
	private Long monthCount;
	private Long monthLimit;
	
	public StatPerMobile(Long dayCount, Long dayLimit, Long monthCount, Long monthLimit) {
		this.dayCount = dayCount;
		this.dayLimit = dayLimit;
		this.monthCount = monthCount;
		this.monthLimit = monthLimit;
	}
	public Long getDayCount() {
		return dayCount;
	}
	public void setDayCount(Long dayCount) {
		this.dayCount = dayCount;
	}
	public Long getDayLimit() {
		return dayLimit;
	}
	public void setDayLimit(Long dayLimit) {
		this.dayLimit = dayLimit;
	}
	public Long getMonthCount() {
		return monthCount;
	}
	public void setMonthCount(Long monthCount) {
		this.monthCount = monthCount;
	}
	public Long getMonthLimit() {
		return monthLimit;
	}
	public void setMonthLimit(Long monthLimit) {
		this.monthLimit = monthLimit;
	}
	

}
