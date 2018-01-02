package com.excilys.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class UtilDate {
	
	@Autowired
	public ResourceBundleMessageSource messageSource;
	

	public UtilDate() {
		super();
	}

	/**
	 * Convert String to Date
	 * @param dateStr the stirng date
	 * @return datetime
	 */
	public DateTime toDate(String dateStr) {
		String pattern = messageSource.getMessage("format.datePattern", null, LocaleContextHolder.getLocale());
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern);
		DateTime date = null;
		if (!(dateStr.isEmpty())) {
			date = dateTimeFormatter.parseDateTime(dateStr);
		}
		return date;
	}
	
	/**
	 * Convert Date to String
	 * @param dateTime the datetime
	 * @return string date
	 */
	public String toString(DateTime dateTime) {
		String pattern = messageSource.getMessage("format.datePattern", null, LocaleContextHolder.getLocale());
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern);
		String date = null;
		if (dateTime != null) {
			return dateTimeFormatter.print(dateTime);
		}
		return date;
	}
}
