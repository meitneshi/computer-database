package com.excilys.dao.impl;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ch.qos.logback.classic.Logger;

import com.excilys.dao.ILogDAO;
import com.jolbox.bonecp.BoneCPDataSource;

@Repository
public class LogDAOImpl implements ILogDAO{

	private final static Logger logger = (Logger) LoggerFactory.getLogger(LogDAOImpl.class);
	
	@Autowired
	private BoneCPDataSource dataSource;
	
	public void create(String logMessage) {
		logger.info("Attempting to log in database");
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		String sql = "INSERT INTO log (id, date, label) "
				+ "VALUES (null, null, ?)";
		try {
			jt.update(sql, new Object [] { logMessage });
			logger.info("log is successfull");
		} catch(DataAccessException e) {
			logger.debug("failed to log in Database "+e.getMessage());
			throw new DataAccessResourceFailureException(e.getMessage());
		}
	}
}
