
package com.n.ysb.service.business.validator.parsAuthValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.n.ysb.service.business.BusRedis;
import com.n.ysb.service.business.pars.BaseParsWithToken;

@Deprecated
public class TokenValidator implements ConstraintValidator<TokenValid, BaseParsWithToken> {
	
	private Logger log = LoggerFactory.getLogger(TokenValidator.class);
	
	@Autowired
	private BusRedis busRedis;

	@Override
	public void initialize(TokenValid constraintAnnotation) {
		log.info("TokenValidator initialize...");
		log.info(busRedis.toString());
	}

	@Override
	public boolean isValid(BaseParsWithToken baseParsWithToken, ConstraintValidatorContext context) {
		log.info(" token isValid begin...");
		if(baseParsWithToken.getMerchantMobile() == null)
			return false;
		
		String mobile = baseParsWithToken.getMerchantMobile().toString();
		String token = StringUtils.defaultString(baseParsWithToken.getToken(), "");
		return busRedis.isTokenValid(mobile, token);
	}

}
