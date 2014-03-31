package com.excilys.service;

import java.sql.ResultSet;

import com.excilys.dao.DAOFactory;
import java.sql.Connection;
import com.mysql.jdbc.PreparedStatement;

public enum FactoryService {
	
	/*--------------------*/
	
		
	
	/*---------------------*/

	INSTANCE;
	
	private DAOFactory daoFactory = DAOFactory.INSTANCE;

	public Connection getConnection() {
		return daoFactory.getConnection();
	}
	
	public void safeClose(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
		daoFactory.safeClose(connection, preparedStatement, resultSet);
	}
}
