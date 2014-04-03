package com.excilys.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

import com.excilys.dao.impl.ConnectionFactory;

public class UtilDate {
	
	static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	private final static Logger logger = (Logger) LoggerFactory.getLogger(ConnectionFactory.class);

	public UtilDate() {
		super();
	}

	//convert string to Date
	public static Date toDate(String dateStr) {
		Date date = null;
		try {
			date = formatter.parse(dateStr);
		} catch (ParseException e) {
			logger.debug("failed to format the date" +e.getMessage());
			return date;
		}
		return date;
	}
	
	//convert Date to String
	public static String toString(Date dateD) {
		
		return null;
	}
}
