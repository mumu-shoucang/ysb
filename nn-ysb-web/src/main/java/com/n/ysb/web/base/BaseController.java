package com.n.ysb.web.base;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

public class BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateEditor());
	}
	
	private class DateEditor extends PropertyEditorSupport {
		
		private String[] patterns = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"};
		
		/**
		 * Parse the Date from the given text, using the specified DateFormat.
		 */
		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			if (!StringUtils.hasText(text)) {
				// Treat empty String as null value.
				setValue(null);
			} else {
				
				try {
					setValue(DateUtils.parseDate(text, patterns));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		/**
		 * Format the Date as String, using the specified DateFormat.
		 */
		@Override
		public String getAsText() {
			Date value = (Date) getValue();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return (value != null ? dateFormat.format(value) : "");
		}
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public final SimpleResponse processException(Exception ex, HttpServletRequest request) {
		logger.error("系统异常: ", ex);
		return SimpleResponse.fail("系统异常:" + ex.getMessage());
	}

}
