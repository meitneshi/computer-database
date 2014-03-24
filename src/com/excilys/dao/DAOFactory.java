/**
 * 
 */
package com.excilys.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * @author mbibos
 * Implement Singleton
 */
public class DAOFactory {

	private String url;
	private String user;
	private String password;
	
	private Connection connection;
	
	private final static DAOFactory _instance = new DAOFactory();
	
	private DAOFactory(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initParam() throws IOException {
		
		/*-----AVEC fichier de config------*/
//		Properties properties = new Properties();
//		FileInputStream fileStream = new FileInputStream("./WebContent/WEB-INF/config/dbConnectionConf.conf");
//		try {
//			properties.load(fileStream);
//		} finally {
//			fileStream.close();
//		}
//		this.url = properties.getProperty("url");
//		this.user = properties.getProperty("user");
//		this.password = properties.getProperty("password");
		/*-----SANS Fichier de config-----*/
		this.url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
		this.user = "root";
		this.password = "jmlld3fpj";
	}

	public static DAOFactory getInstance() {
		return _instance;
	}

	public Connection getConnection() {
		try {
			this.initParam();
			return (Connection) DriverManager.getConnection(url, user, password);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void safeClose(Connection connection, Statement statement, ResultSet resultSet){
		try {
			if (connection != null) {
				connection.close();
			}
			if (resultSet != null){
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//---test main----//
//	public static void main(String args[]) {
//		ConnectionManager connMan = new ConnectionManager();
//		try {
//			ResultSet res = connMan.connection.createStatement().executeQuery("SELECT * FROM company");
//			while (res.next()){
//				System.out.println(res.getString(2));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
}