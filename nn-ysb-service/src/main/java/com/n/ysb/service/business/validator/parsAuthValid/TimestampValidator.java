package com.n.ysb.service.business.validator.parsAuthValid;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Deprecated
public class TimestampValidator implements ConstraintValidator<TimestampValid, String> {

	private Logger log = LoggerFactory.getLogger(TimestampValidator.class);
	
	@Autowired
	private AppKey appKey;
	
	private int sec = 6;
	@Override
	public void initialize(TimestampValid constraintAnnotation) {
		log.info("TimestampValidator initialize...");
		sec = NumberUtils.toInt(appKey.getRequestValidSec(), 6);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		log.info("timestamp isValid begin...");
		Long now = Long.valueOf(formatDate());
		Long request = Long.valueOf(value);
		if(now - request > sec)
			return false;
		return true;
	}
	
	private String formatDate() {
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        return formatter.format(new Date());
	}

}
