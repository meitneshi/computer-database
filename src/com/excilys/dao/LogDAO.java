package com.excilys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

public enum LogDAO {

	INSTANCE;
			
	private final static Logger logger = (Logger) LoggerFactory.getLogger(DAOFactory.class);
	private DAOFactory daoFactory = DAOFactory.INSTANCE;

	public void create(String logMessage) {
		logger.info("Attempting to log in database");
		Connection connection = DAOFactory.INSTANCE.getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO log (id, date, label) "
				+ "VALUES (null, null, ?)";
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			preparedStatement.setString(1, logMessage);
			preparedStatement.executeUpdate();
			logger.info("log is successfull");
		} catch (SQLException e) {
			logger.debug("failed to log in Database "+e.getMessage());
		} finally {
			daoFactory.safeClose(null, preparedStatement, null);
		}
	}
}
