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
@Constraint(validatedBy=com.n.ysb.service.business.validator.parsAuthValid.FileTypeValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FileTypeValid {

	String message() default "file type invalid";
	
	Class<?>[] groups() default {};  
    Class<? extends Payload>[] payload() default {};  
}
