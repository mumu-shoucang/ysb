package com.n.ysb.service.business.validator.parsAuthValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Deprecated
public class FileTypeValidator implements ConstraintValidator<FileTypeValid, Object> {

	private static final Logger log = LoggerFactory.getLogger(FileTypeValidator.class);
	
	@Override
	public void initialize(FileTypeValid constraintAnnotation) {
		log.info("FileTypeValidator initialize...");
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		log.info("FileTypeValidator isValid begin...");
		if(obj instanceof CommonsMultipartFile) {
			CommonsMultipartFile parFile = (CommonsMultipartFile) obj;
			String fileName = parFile.getOriginalFilename();
			if(fileName.endsWith("png") || fileName.endsWith("jpeg") || fileName.endsWith("jpg")) {
				return true;
			}
			return false;
		}
		return false;
	}
	
}
