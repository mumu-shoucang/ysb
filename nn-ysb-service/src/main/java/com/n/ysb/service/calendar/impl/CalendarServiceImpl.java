package com.n.ysb.service.calendar.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.n.ysb.service.calendar.CalendarRedis;
import com.n.ysb.service.calendar.ICalendarService;
import com.n.ysb.service.calendar.mapper.NnCalendarMapper;
import com.n.ysb.service.calendar.po.NnCalendar;
import com.n.ysb.service.calendar.po.NnCalendarExample;
import com.n.ysb.service.calendar.po.NnCalendarExample.Criteria;
import com.n.ysb.service.calendar.vo.NnCalendarVo;

@Service
public class CalendarServiceImpl implements ICalendarService{
	
	private final Logger log = LoggerFactory.getLogger(getClass());	
	
	@Autowired
	private NnCalendarMapper calendarMapper;
	@Autowired
	private CalendarRedis calendarRedis;
	
	@Override
	public Map<String, Object> addCalendar(NnCalendar po) {
		Map<String, Object> map = new HashMap<String,Object>();
		boolean isSuc =false;
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(po.getCalendar());
		try {
			po.setCalendar(formatter.parse(dateString));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int insert = calendarMapper.insert(po);
		if(insert>0){
			// into redis
			if(po.getStatus().equals("0")){
				calendarRedis.addWorkday(dateString);
			}else{
				calendarRedis.addNonWorkday(dateString);
			}
			isSuc = true;
			map.put("添加成功", isSuc);
		}else{
			map.put("添加失败", isSuc);
		}
		log.info("插入日期-:{},成功/失败-:{}","日期:"+po.getCalendar()+"类型:"+po.getStatus(),isSuc);
		return map;
	}
	
	
	@Override
	public Map<String, Object> deleteCalendar(NnCalendar po) {
		Map<String, Object> map = new HashMap<String,Object>();
		boolean isSuc =false;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(po.getCalendar());
		String type = po.getStatus();
		int key = calendarMapper.deleteByPrimaryKey(po.getId());
		if(key>0){
			if(type.equals("0")){
				calendarRedis.delWorkday(dateString);
			}else{
				calendarRedis.delNonWorkday(dateString);
			}	
			isSuc = true;
			map.put("删除成功", isSuc);
		}else{
			map.put("删除失败", isSuc);
		}
		
		log.info("删除日期-:{},成功/失败-:{}","日期:"+po.getCalendar()+"类型:"+po.getStatus(),isSuc);
		return map;
	}
	
	@Override
	public PageInfo<NnCalendar> getCalendarPage(NnCalendarVo vo, int pageNo,
			int limit) {
		Page<NnCalendar> startPage = PageHelper.startPage(pageNo, limit);
    	NnCalendarExample example = new NnCalendarExample();
    	Criteria criteria = example.createCriteria();
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date calendar = null;
    
		// 类型
    	if(StringUtils.isNotBlank(vo.getStatus())){
			criteria.andStatusEqualTo(vo.getStatus());
		}
		
		try {
			if(StringUtils.isNotBlank(vo.getTime())){
				calendar = sdf.parse(vo.getTime()+" 00:00:00");
				criteria.andCalendarEqualTo(calendar);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		example.setOrderByClause("CALENDAR desc");
		
		List<NnCalendar> selectByExample = calendarMapper.selectByExample(example);
		log.info("===========日期分页查询:" + selectByExample);
		return startPage.toPageInfo();
	}

}
