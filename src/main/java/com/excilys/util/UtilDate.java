package com.excilys.util;

import java.text.SimpleDateFormat;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import ch.qos.logback.classic.Logger;

import com.excilys.dao.impl.ConnectionFactory;

public class UtilDate {
	
	@Bean(name = "messageSource")
	public static ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
	    bean.setBasename("messages");
	    return bean;
	}
	
	
//	static String pattern = messageSource().getMessage("format.datePattern", null, LocaleContextHolder.getLocale());
//	static SimpleDateFormat formatter = new SimpleDateFormat(pattern);
	
	private final static Logger logger = (Logger) LoggerFactory.getLogger(ConnectionFactory.class);

	public UtilDate() {
		super();
	}

	//convert string to Date
	public static DateTime toDate(String dateStr) {
		String pattern = messageSource().getMessage("format.datePattern", null, LocaleContextHolder.getLocale());
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern);
		DateTime date = null;
		formatter.setLenient(false);
		if (!"".equals(dateStr)) {
			date = dateTimeFormatter.parseDateTime(dateStr);
		}
		return date;
	}
	
	//convert Date to String
	public static String toString(DateTime dateTime) {
		String pattern = messageSource().getMessage("format.datePattern", null, LocaleContextHolder.getLocale());
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern);
		String date = null;
		formatter.setLenient(false);
		if (dateTime != null) {
			return dateTimeFormatter.print(dateTime);
		}
		return date;
	}
}
