package com.n.ysb.service.business.core;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n.ysb.service.calendar.CalendarRedis;

@Service
public class WorkDayService {

	@Autowired
	private CalendarRedis calendarRedis;
	
	public boolean isLawHoliday(String date) {
		List<String> lawHolidays = calendarRedis.getNonWorkday();
        if (lawHolidays.contains(date)) { 
            return true; 
        } 
        return false; 
    }
	
	public boolean isExtraWorkday(String date) {
		List<String> extraWorkdays = calendarRedis.getWorkday();
		if (extraWorkdays.contains(date)) { 
			return true; 
		} 
		return false; 
	}
	
	public boolean isWeekends(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        if (ca.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || ca.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) { 
            return true; 
        } 
        return false; 
	}
	
	public boolean isHoliday(Date date) {
		String dates = parseDate(date, "yyyy-MM-dd");
		// 首先法定节假日必定是休息日
		if (this.isLawHoliday(dates)) { 
		    return true; 
		}
		// 所有周末中只有非补班的才是休息日 
		if (this.isExtraWorkday(dates)) { 
		    return false; 
		} 
		// 排除法定节假日外的非周末必定是工作日 
		if (!this.isWeekends(date)) { 
		    return false; 
		} 
		
		return true;
	}
	
	private String parseDate(Date date, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
	}
}
