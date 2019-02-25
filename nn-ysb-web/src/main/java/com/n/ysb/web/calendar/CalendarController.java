package com.n.ysb.web.calendar;

import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.n.ysb.service.calendar.ICalendarService;
import com.n.ysb.service.calendar.po.NnCalendar;
import com.n.ysb.service.calendar.vo.NnCalendarVo;
import com.n.ysb.web.base.BaseController;
import com.n.ysb.web.base.SimpleResponse;

@Controller
@RequestMapping("/calendar")
public class CalendarController  extends BaseController{
	
	@Autowired
	private ICalendarService calendarService;
	
	@RequestMapping("addCalendar")
	@ResponseBody
	public SimpleResponse addCalendar(NnCalendar po){
		Map<String, Object> addCalendar = calendarService.addCalendar(po);
		return SimpleResponse.suc(addCalendar);
	}
	
	@RequestMapping("deleteCalendar")
	@ResponseBody
	public SimpleResponse deleteCalendar(NnCalendar po){
		Map<String, Object> deleteCalendar = calendarService.deleteCalendar(po);
		return SimpleResponse.suc(deleteCalendar);
	}
	
	
	@RequestMapping("getCalendarPage")
	@ResponseBody
	public SimpleResponse getCalendarPage(NnCalendarVo vo, 
		  @RequestParam(required = false, defaultValue = "1", value = "pageNo") int pageNo,
          @RequestParam(required = false, defaultValue = "10", value = "limit") int limit
	      )throws ParseException{
		PageInfo<NnCalendar> calendarPage = calendarService.getCalendarPage(vo, pageNo, limit);
		return SimpleResponse.suc(calendarPage);
	}

}
