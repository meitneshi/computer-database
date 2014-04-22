package com.excilys.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

public class DateConstraintValidator implements ConstraintValidator<DateValid, String> {
    
	@Bean(name = "messageSource")
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
	    bean.setBasename("messages");
	    return bean;
	}
	
	@Override
	public void initialize(DateValid dateValid) {
	}

	@Override
	public boolean isValid(String date, ConstraintValidatorContext cxt) {
		
		String pattern = messageSource().getMessage("format.datePattern", null, LocaleContextHolder.getLocale());
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		
		String dateRegexFr = "^|(0[1-9]|[1-2][0-9]|3[0-1])-(0[1-9]|1[0-2])-[0-9]{4}$";
		String dateRegexEn = "^|(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])-[0-9]{4}$";
		
		if (date.isEmpty() || date == null) {
			return true;
		}
		if (date.matches(dateRegexFr) || date.matches(dateRegexEn)) {
			formatter.setLenient(false);
			try {
				formatter.parse(date);
			} catch (ParseException e) {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
}
