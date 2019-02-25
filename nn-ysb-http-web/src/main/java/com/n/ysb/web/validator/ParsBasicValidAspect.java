package com.n.ysb.web.validator;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.n.ysb.service.business.pars.base.ReturnCode;
import com.n.ysb.service.business.pars.base.ReturnMap;
import com.n.ysb.web.base.SimpleResponse;

/**
 * 参数基本校验切面
 */
@Deprecated
@Aspect
@Component
public class ParsBasicValidAspect {
	
	private static final Logger log = LoggerFactory.getLogger(ParsBasicValidAspect.class);
	
	@PostConstruct
	private void init() {
		log.info("ParsValidAspect init...");
	}

	@Around("execution(* com.n.ysb.web.business.ReceiveApi.*(..) ) && args(.., bindingResult)")
	public Object doAround(ProceedingJoinPoint pjp, BindingResult bindingResult) throws Throwable {
		log.info("ParsValidAspect do around...");
		if (bindingResult.hasErrors())
			return buildResponse(bindingResult);
		else
			return pjp.proceed();
	}

	private SimpleResponse buildResponse(BindingResult result) {
		if (result.hasErrors()) {
			List<ObjectError> allErrors = result.getAllErrors();
			List<String> errors = new ArrayList<>();
			for (ObjectError objectError : allErrors) {
				errors.add(objectError.getDefaultMessage());
			}
			return SimpleResponse.suc(ReturnMap.New(ReturnCode.pars_auth_invalid.getCode(), ReturnCode.pars_auth_invalid.getDesc(), errors));
		}
		return null;
	}

}
