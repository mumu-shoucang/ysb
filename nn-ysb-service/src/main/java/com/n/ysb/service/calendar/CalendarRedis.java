package com.n.ysb.service.calendar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CalendarRedis {

	private static final String workday_key = "workday";
	private static final String nonworkday_key = "nonworkday";
	
	@Autowired
	private StringRedisTemplate redis;
	
	public void addWorkday(String date) {
		redis.boundListOps(workday_key).leftPush(date);
	}
	
	public void addNonWorkday(String date) {
		redis.boundListOps(nonworkday_key).leftPush(date);
	}
	
	public void delWorkday(String date) {
		redis.boundListOps(workday_key).remove(0, date);
	}
	
	public void delNonWorkday(String date) {
		redis.boundListOps(nonworkday_key).remove(0, date);
	}
	
	public List<String> getWorkday() {
		return redis.boundListOps(workday_key).range(0, -1);
	}
	
	public List<String> getNonWorkday() {
		return redis.boundListOps(nonworkday_key).range(0, -1);
	}
}
