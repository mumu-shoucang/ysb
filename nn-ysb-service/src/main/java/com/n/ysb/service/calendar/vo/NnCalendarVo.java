package com.n.ysb.service.calendar.vo;

import java.util.Date;

public class NnCalendarVo {
	private String id;

    private Date calendar;

    private String status;
    
    private String time;
    
    

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getCalendar() {
        return calendar;
    }

    public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setCalendar(Date calendar) {
        this.calendar = calendar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}