package com.excilys.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

public class UtilDate {
	
	@Bean(name = "messageSource")
	public static ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
	    bean.setBasename("messages");
	    return bean;
	}
	

	public UtilDate() {
		super();
	}

	//convert string to Date
	public static DateTime toDate(String dateStr) {
		String pattern = messageSource().getMessage("format.datePattern", null, LocaleContextHolder.getLocale());
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern);
		DateTime date = null;
		if (!"".equals(dateStr)) {
			date = dateTimeFormatter.parseDateTime(dateStr);
		}
		return date;
	}
	
	//convert Date to String
	public static String toString(DateTime dateTime) {
		String pattern = messageSource().getMessage("format.datePattern", null, LocaleContextHolder.getLocale());
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern);
		String date = null;
		if (dateTime != null) {
			return dateTimeFormatter.print(dateTime);
		}
		return date;
	}
}
