package com.n.ysb.service.business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

@Service
public class BusRedis {

	
	private static final Logger log = LoggerFactory.getLogger(BusRedis.class);
	
	@Autowired
	private StringRedisTemplate redis;
	
	@Value("${sms.count.per24H}")
	private int smsCountPer24H = 6;
	
	@PostConstruct
	private void init() {
		log.info("BusRedis init...");
		log.info(smsCountPer24H + "");
	}
	
	public StatPerMobile getStat(String mobile) {
		Date now = new Date();
		String cday = parseDate(now, "yyyyMMdd");
		String cmonth = parseDate(now, "yyyyMM");
		
		Map<Object, Object> map = redis.boundHashOps("stat:"+mobile).entries();
		Long dayCount = NumberUtils.toLong(Objects.toString(map.get(cday+"count"), "0"), 0L);
		Long dayLimit = NumberUtils.toLong(Objects.toString(map.get(cday+"limit"), "0"), 0L);
		Long monthCount = NumberUtils.toLong(Objects.toString(map.get(cmonth+"count"), "0"), 0L);
		Long monthLimit = NumberUtils.toLong(Objects.toString(map.get(cmonth+"limit"), "0"), 0L);
		return new StatPerMobile(dayCount, dayLimit, monthCount, monthLimit);
	}
	
	/**
	 *  stat:13521314803{20180717count:100; 20180717limit:1000; 201807count:300; 201807limit:3000}
	 */
	public void statByMobile(String mobile, BigDecimal orderAmt) {
		BigDecimal newAmt = orderAmt.setScale(0, RoundingMode.HALF_UP);
		String amtStr = newAmt.toString();
		Date now = new Date();
		String cday = parseDate(now, "yyyyMMdd");
		String cmonth = parseDate(now, "yyyyMM");
		List<String> keys = new ArrayList<String>();
		keys.add(cday);
		keys.add(cmonth);
		redis.execute(new RedisScript<Long>(){
			@Override
			public String getScriptAsString() {
				String script = " "
						+ " local cday, cmonth = KEYS[1], KEYS[2]; "
						+ " local mobile, orderAmt = ARGV[1], ARGV[2]; "
						+ " redis.call('hincrby', 'stat:'..mobile, cday..'limit', orderAmt); "
						+ " redis.call('hincrby', 'stat:'..mobile, cday..'count', 1); "
						+ " redis.call('hincrby', 'stat:'..mobile, cmonth..'limit', orderAmt); "
						+ " local c = redis.call('hincrby', 'stat:'..mobile, cmonth..'count', 1); "
						+ " if(c==1) then  redis.call('expire', 'stat:'..mobile, 5184000)  end; "; // 60Day
				return script;
			}

			@Override
			public String getSha1() {
				return "statByMobile";
			}

			@Override
			public Class<Long> getResultType() {
				return Long.class;
			}
			
		}, keys, new Object[]{mobile, amtStr});
	}
	
	public boolean canSendSmsCode(String mobile) {
		String count = redis.boundValueOps("smscount:" + mobile).get();
		int c = NumberUtils.toInt(count, 0);
		return c < smsCountPer24H ? true : false;
	}
	
	public void setTransferSmsCode(String mobile, String smsCode) {
		List<String> keys = new ArrayList<String>();
		
		redis.execute(new RedisScript<Long>(){
			@Override
			public String getScriptAsString() {
				String script = " "
						+ " local mobile, smsCode = ARGV[1], ARGV[2]; "
						+ " redis.call('set', 'smstransfer:'..mobile, smsCode); "
						+ " redis.call('expire', 'smstransfer:'..mobile, 1200); ";  // seconds 20M
				return script;
			}

			@Override
			public String getSha1() {
				return "setTransferSmsCode";
			}

			@Override
			public Class<Long> getResultType() {
				return Long.class;
			}
			
		}, keys, new Object[]{mobile, smsCode});
	}
	
	public void setSmsCode(String mobile, String smsCode) {
		List<String> keys = new ArrayList<String>();
		
		redis.execute(new RedisScript<Long>(){
			@Override
			public String getScriptAsString() {
				String script = " "
						+ " local mobile, smsCode = ARGV[1], ARGV[2]; "
						+ " redis.call('set', 'sms:'..mobile, smsCode); "
						+ " redis.call('expire', 'sms:'..mobile, 600); " // seconds 10M
						+ " local c = redis.call('incr', 'smscount:'..mobile); "
						+ " if(c==1) then  redis.call('expire', 'smscount:'..mobile, 86400)  end; "; // 24H
				return script;
			}

			@Override
			public String getSha1() {
				return "setSmsCode";
			}

			@Override
			public Class<Long> getResultType() {
				return Long.class;
			}
			
		}, keys, new Object[]{mobile, smsCode});
	}
	
	public String getSmsCode(String mobile) {
		String sms = redis.boundValueOps("sms:" + mobile).get();
		return StringUtils.defaultIfEmpty(sms, "");
	}
	
	public String getTransferSmsCode(String mobile) {
		String sms = redis.boundValueOps("smstransfer:" + mobile).get();
		return StringUtils.defaultIfEmpty(sms, "");
	}
	
	public void setToken(String mobile, String token) {
		redis.boundValueOps("token:" + mobile).set(token, 24, TimeUnit.HOURS);
	}
	
	public void delToken(String mobile) {
		redis.delete("token:" + mobile);
	}
	
	public boolean isTokenValid(String mobile, String token) {
		String redisToken = redis.boundValueOps("token:" + mobile).get();
		log.info("get token from redis:{} - {}", mobile, redisToken);
		return token.equals(redisToken);
	}
	
	public String getToken(String mobile) {
		String redisToken = redis.boundValueOps("token:" + mobile).get();
		log.info("get token from redis:{} - {}", mobile, redisToken);
		return StringUtils.defaultIfEmpty(redisToken, "");
	}
	
	private String parseDate(Date date, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
	}
}
