/**
 * 
 */
package com.excilys.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.qos.logback.classic.Logger;

import com.excilys.exceptions.IllegalPersonnalException;
import com.jolbox.bonecp.BoneCPDataSource;


/**
 * @author mbibos
 */
@Repository
public class ConnectionFactory {
	
	public ConnectionFactory() {
		super();
	}

	private final Logger logger = (Logger) LoggerFactory.getLogger(ConnectionFactory.class);
	
	@Autowired
	private BoneCPDataSource connectionPool;

	private ThreadLocal<Connection> connectionTL = new ThreadLocal<Connection>(){
		@Override
		protected Connection initialValue() {
			Connection connection = null;
			try {
				logger.info("Connection initialize successfully");
				connection = connectionPool.getConnection();
			} catch (SQLException e) {
				logger.debug("failed to initialize connection "+e.getMessage());
				throw new IllegalPersonnalException();
			}
			return connection;
		}
	};
	
	public Connection getConnection() {
		return connectionTL.get();
	}

	/**
	 * Close safely in the right order the given parameters
	 * @param connection
	 * @param preparedStatement
	 * @param resultSet
	 */
	public void safeClose(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
		logger.info("attempting to close safe");
		try {
			if (connection != null) {
				connection.close();
				connectionTL.remove();
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
	
	/**
	 * close the connection
	 */
	public void closeConnection() {
		logger.info("attempting to close connection");
		try {
			this.getConnection().close();
			connectionTL.remove();
			logger.info("Connection closed");
		} catch (SQLException e) {
			logger.debug("Safe Close failed "+e.getMessage());
			throw new IllegalPersonnalException();
		}	
	}
	
	/**
	 * Start a new transaction
	 */
	public void startTransaction() {
		logger.info("starting transaction ....");
		try {
			this.getConnection().setAutoCommit(false);
		} catch (IllegalPersonnalException | SQLException e) {
			logger.debug("the transaction is not started " + e.getMessage());
			throw new IllegalPersonnalException();
		}
	}

	/**
	 * Commit the transaction
	 */
	public void commit() {
		try {
			this.getConnection().commit();
			logger.info("commit successfull");
		} catch (SQLException e) {
			logger.debug("commit failed "+e.getMessage());
			throw new IllegalPersonnalException();
		}
	}

	/**
	 * Rollback the transaction
	 */
	public void rollback() {
		try {
			this.getConnection().rollback();
			logger.info("rollback successfull");
		} catch (SQLException e) {
			logger.debug("rollback failed "+e.getMessage());
			throw new IllegalPersonnalException();
		}		
	}
}
