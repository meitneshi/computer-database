package com.excilys.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class DAOFactory {

	public DAOFactory() {
		
	}
	
	public void safeClose(Connection connection, Statement statement, ResultSet resultSet){
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
}
