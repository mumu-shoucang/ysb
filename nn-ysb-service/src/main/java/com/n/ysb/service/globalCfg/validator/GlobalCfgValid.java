package com.n.ysb.service.globalCfg.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.BigDecimal;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.apache.commons.lang3.StringUtils;

import com.n.ysb.service.globalCfg.vo.NnGlobalCfgVo;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GlobalCfgValid.ValidNotNullAndFormat.class)
@Documented
public @interface GlobalCfgValid {
    
    String message() default "The value is invalid.";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default{};
    
    
    class ValidNotNullAndFormat implements ConstraintValidator<GlobalCfgValid,NnGlobalCfgVo>{

        @Override
        public void initialize(GlobalCfgValid constraintAnnotation) {
            
        }

        @Override
        public boolean isValid(NnGlobalCfgVo globalCfgVo, ConstraintValidatorContext context) {
            if(globalCfgVo.getSingleLimit() instanceof Long){
                return true;
            }else if(globalCfgVo.getDayLimit() instanceof Long){
                return true;
            }else if(globalCfgVo.getMonthLimit() instanceof Long){
                return true;
            }else if(globalCfgVo.getDayCount() instanceof Long){
                return true;
            }else if(globalCfgVo.getMonthCount() instanceof Long){
                return true;
            }else if(globalCfgVo.getTradeFee() instanceof BigDecimal){
                return true;
            }else if(globalCfgVo.getT0WithdrawFee() instanceof BigDecimal){
                return true;
            }else if(globalCfgVo.getT0WithdrawWorkdayFee() instanceof BigDecimal){
                return true;
            }else if(globalCfgVo.getT0WithdrawNonworkdayFee() instanceof BigDecimal){
                return true;
            }else{
                return false;
            }
        }
        
    }

}
