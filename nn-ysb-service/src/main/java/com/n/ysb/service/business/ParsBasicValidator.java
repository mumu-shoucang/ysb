package com.n.ysb.service.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n.ysb.service.business.pars.base.ReturnCode;
import com.n.ysb.service.business.pars.base.ReturnMap;
import com.n.ysb.service.business.pars.base.Sign;
import com.n.ysb.service.business.validator.parsAuthValid.AppKey;

@Service
public class ParsBasicValidator {
	
	private static final String datePattern = "yyyyMMddHHmmss";
	
	@Autowired
	private AppKey appKey;
	@Autowired
	private BusRedis busRedis;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	public Map<String, Object> valid(Sign pars) {
		
		log.info("ParsBasicValidator accept pars:{} ", pars.toString());
		
		// base check
		if(pars.hasNull()) {
			return ReturnMap.New(ReturnCode.pars_auth_invalid.getCode(), ReturnCode.pars_auth_invalid.getDesc());
		}
		
		try {
			// timestamp
			String[] datePatterns = new String[]{datePattern};
			String now = formatDate();
			String request = pars.getTimestamp();
			Date nowD = DateUtils.parseDate(now, datePatterns);
			Date requestD = DateUtils.parseDate(request, datePatterns);
			String insec = appKey.getRequestValidSec();
			Long used = nowD.getTime() - requestD.getTime();
			if(used > NumberUtils.toLong(insec, 8) * 1000) {
				log.info("time out: {}-{}-{}", now, request, insec);
				return ReturnMap.New(ReturnCode.pars_auth_invalid.getCode(), "请求超时，请重试");
			}
		} catch (Exception ex) {
			throw new RuntimeException("日期转换异常，抛出，由basecontroller统一处理");
		}
		
		// sign
		if(!pars.getSign().equalsIgnoreCase(pars.sign(appKey.getMd5key()))) {
			return ReturnMap.New(ReturnCode.pars_auth_invalid.getCode(), "签名非法");
		}
		
		// token
		if(pars.isNeedCheckToken()) {
			String mobile = pars.getMerchantMobile().toString();
			String token = StringUtils.defaultString(pars.getToken(), "");
			String redisToken = busRedis.getToken(mobile);
			if(StringUtils.isBlank(redisToken)) {
				return ReturnMap.New(ReturnCode.pars_auth_invalid.getCode(), "登录已过期，请重新登录");
			}
			if(!token.equals(redisToken)) {
				return ReturnMap.New(ReturnCode.pars_auth_invalid.getCode(), "已在其他设备登录，请重新登录");
			}
//			boolean isTokenValid = busRedis.isTokenValid(mobile, token);
//			if(!isTokenValid) {
//				log.info("token is invalid {} -- {}", mobile, token);
//				return ReturnMap.New(ReturnCode.pars_auth_invalid.getCode(), "token invalid!");
//			}
		}
		
		return ReturnMap.suc();
		
	}
	
	private String formatDate() {
		final SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
        return formatter.format(new Date());
	}

}
