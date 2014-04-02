package com.excilys.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.qos.logback.classic.Logger;

import com.excilys.dao.ILogDAO;

@Repository
public class LogDAOImpl implements ILogDAO{

	private final static Logger logger = (Logger) LoggerFactory.getLogger(ConnectionFactory.class);
	
	@Autowired
	private ConnectionFactory daoFactory;

	public void create(String logMessage) {
		logger.info("Attempting to log in database");
		Connection connection = daoFactory.getConnection();
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
