package com.excilys.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	
	static String pattern = messageSource().getMessage("format.datePattern", null, LocaleContextHolder.getLocale());
	static SimpleDateFormat formatter = new SimpleDateFormat(pattern);
	
	private final static Logger logger = (Logger) LoggerFactory.getLogger(ConnectionFactory.class);

	public UtilDate() {
		super();
	}

	//convert string to Date
	public static Date toDate(String dateStr) {
		Date date = null;
		formatter.setLenient(false);
		try {
			formatter.setLenient(false);
			date = formatter.parse(dateStr);
		} catch (ParseException e) {
			logger.debug("failed to format the date " +e.getMessage());
			return date;
		}
		return date;
	}
	
	//convert Date to String
	public static String toString(Date dateD) {
		String date = null;
		formatter.setLenient(false);
		if (dateD != null) {
			return formatter.format(dateD);
		}
		return date;
	}
}
