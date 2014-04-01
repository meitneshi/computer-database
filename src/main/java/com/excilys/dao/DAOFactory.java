/**
 * 
 */
package com.excilys.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

import com.excilys.exceptions.IllegalPersonnalException;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import java.sql.PreparedStatement;


/**
 * @author mbibos
 * Implement Singleton
 */
public enum DAOFactory {
	
	INSTANCE;
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(DAOFactory.class);
	
	private BoneCP connectionPool = null;
	/*---------------------------*/

	{
		logger.info("searching for Driver...");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			logger.debug("Driver found");
			
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull"); 
			config.setUsername("root"); 
			config.setPassword("jmlld3fpj");
			config.setMinConnectionsPerPartition(1);
			config.setMaxConnectionsPerPartition(10);
			config.setPartitionCount(1);
			connectionPool = new BoneCP(config);
		} catch (ClassNotFoundException e) {
			logger.debug("Driver not found "+e.getMessage());
			throw new IllegalPersonnalException();
		} catch (SQLException e) {
			logger.debug("Connection Pool failed "+e.getMessage());
			throw new IllegalPersonnalException();
		}		
	}
	
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
	
	public void closeConnection() {
		logger.info("attempting to close connection");
		try {
			DAOFactory.INSTANCE.getConnection().close();
			connectionTL.remove();
			logger.info("Connection closed");
		} catch (SQLException e) {
			logger.debug("Safe Close failed "+e.getMessage());
			throw new IllegalPersonnalException();
		}	
	}
	
	public void startTransaction() {
		logger.info("starting transaction ....");
		try {
			this.getConnection().setAutoCommit(false);
		} catch (IllegalPersonnalException | SQLException e) {
			logger.debug("the transaction is not started " + e.getMessage());
			throw new IllegalPersonnalException();
		}
		
	}

	public void commit() {
		try {
			this.getConnection().commit();
			logger.info("commit successfull");
		} catch (SQLException e) {
			logger.debug("commit failed "+e.getMessage());
			throw new IllegalPersonnalException();
		}
	}

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
