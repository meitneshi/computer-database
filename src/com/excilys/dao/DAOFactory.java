/**
 * 
 */
package com.excilys.dao;

//import java.io.FileInputStream;		//uncomment when using config file
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Properties;			//uncomment when using config file





import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

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
	
	private Connection connection = null;
	private BoneCP connectionPool = null;
	/*---------------------------*/

	{
		logger.info("searching for Driver...");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			logger.debug("Driver found");
		} catch (Exception e) {
			logger.debug("Driver not found "+e.getMessage());
		}
	}
	
	public Connection getConnection() {
		logger.info("attempting to get a connexion");
		try {
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull"); 
			config.setUsername("root"); 
			config.setPassword("jmlld3fpj");
			config.setMinConnectionsPerPartition(1);
			config.setMaxConnectionsPerPartition(10);
			config.setPartitionCount(1);
			connectionPool = new BoneCP(config); 
			
			connection = connectionPool.getConnection();
			logger.info("connexion established, trying to shutdown connexion Pool");
			connectionPool.shutdown();
			logger.info("connexion Pool shutdown successfully");
		} catch (SQLException e) {
			logger.debug("failed while getting connexion "+ e.getMessage());
		} 
		return connection;
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
		} finally {
			
		}
	}
}
