package com.n.ysb.service.business.validator.parsAuthValid;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Deprecated
@Constraint(validatedBy=com.n.ysb.service.business.validator.parsAuthValid.TimestampValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface TimestampValid {

	String message() default "time out, please retry";
	
	Class<?>[] groups() default {};  
    Class<? extends Payload>[] payload() default {};  
}
