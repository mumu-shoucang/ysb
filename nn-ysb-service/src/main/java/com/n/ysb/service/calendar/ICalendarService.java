package com.n.ysb.service.calendar;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.n.ysb.service.calendar.po.NnCalendar;
import com.n.ysb.service.calendar.vo.NnCalendarVo;

public interface ICalendarService {
	
	Map<String, Object> addCalendar(NnCalendar po);
	
	Map<String, Object> deleteCalendar(NnCalendar po);

	PageInfo<NnCalendar> getCalendarPage(NnCalendarVo vo,int pageNo, int limit);
}
