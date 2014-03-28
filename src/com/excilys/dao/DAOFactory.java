/**
 * 
 */
package com.excilys.dao;

//import java.io.FileInputStream;		//uncomment when using config file
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Properties;			//uncomment when using config file



import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * @author mbibos
 * Implement Singleton
 */
public enum DAOFactory {
	
	INSTANCE;
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(DAOFactory.class);

	private String url;
	private String user;
	private String password;
	
	private Connection connection;
	
	{
		logger.info("searching for Driver...");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			logger.debug("Driver found");
		} catch (Exception e) {
			logger.debug("Driver not found");
			e.printStackTrace();
		}
	}
	
	private void initParam() throws IOException {
		/*-----AVEC fichier de config------*/
		// Properties properties = new Properties();
		// FileInputStream fileStream = new FileInputStream("./WebContent/WEB-INF/config/dbConnectionConf.conf");
		// try {
		// properties.load(fileStream);
		// } finally {
		// fileStream.close();
		// }
		// this.url = properties.getProperty("url");
		// this.user = properties.getProperty("user");
		// this.password = properties.getProperty("password");
		/*-----SANS Fichier de config-----*/
		this.url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
		this.user = "root";
		this.password = "jmlld3fpj";
	}

	public Connection getConnection() {
		logger.info("trying to get connection...");
		try {
			this.initParam();
			return (Connection) DriverManager.getConnection(url, user, password);
		} catch (IOException | SQLException e) {
			logger.debug("failed to get connection "+e.getMessage());
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
