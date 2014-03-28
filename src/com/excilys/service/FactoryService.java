package com.excilys.service;

import java.sql.ResultSet;

import com.excilys.dao.DAOFactory;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class FactoryService {

	protected FactoryService() {
		super();
	}
	
	private DAOFactory daoFactory = DAOFactory.getInstance();

	public Connection getConnection() {
		return daoFactory.getConnection();
	}
	
	public static void safeClose(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
		DAOFactory.safeClose(connection, preparedStatement, resultSet);
	}
}
