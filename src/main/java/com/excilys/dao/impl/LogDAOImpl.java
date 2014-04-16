package com.excilys.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import ch.qos.logback.classic.Logger;

import com.excilys.dao.ILogDAO;
import com.excilys.exceptions.IllegalPersonnalException;
import com.jolbox.bonecp.BoneCPDataSource;

@Repository
public class LogDAOImpl implements ILogDAO{

	private final static Logger logger = (Logger) LoggerFactory.getLogger(ConnectionFactory.class);
	
	@Autowired
	private BoneCPDataSource dataSource;
	
	public void create(String logMessage) {
		logger.info("Attempting to log in database");
		Connection connection = DataSourceUtils.getConnection(dataSource);
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
			throw new DataAccessResourceFailureException(e.getMessage());
		} finally {
			this.safeClose(null, preparedStatement, null);
		}
	}
	
	public void safeClose(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
		logger.info("attempting to close safe");
		try {
			if (connection != null) {
				connection.close();
				logger.info("connection closed");
			}
			if (resultSet != null){
				resultSet.close();
				logger.info("resultSet closed");
			}
			if (preparedStatement != null) {
				preparedStatement.close();
				logger.info("presparedStatement closed");
			}
		} catch (SQLException e) {
			logger.debug("Safe Close failed "+e.getMessage());
			throw new IllegalPersonnalException();
		}
	}
}
