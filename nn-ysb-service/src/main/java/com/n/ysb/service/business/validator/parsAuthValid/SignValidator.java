package com.n.ysb.service.business.validator.parsAuthValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.n.ysb.service.business.pars.base.Sign;

@Deprecated
public class SignValidator implements ConstraintValidator<SignValid, Object> {

	private static final Logger log = LoggerFactory.getLogger(SignValidator.class);
	
	@Autowired
	private AppKey appKey;
	
	private String md5key;
	
	@Override
	public void initialize(SignValid constraintAnnotation) {
		log.info("SignValidator initialize...");
		this.md5key = appKey.getMd5key();
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		log.info("sign isValid begin...");
		if(obj instanceof Sign) {
			Sign parObj = (Sign)obj;
			String localSign = parObj.sign(md5key).toUpperCase();
			String requestSign = parObj.getSign().toUpperCase();
			if(localSign.equals(requestSign)) {
				return true;
			}
		}
		return false;
	}
	
}
