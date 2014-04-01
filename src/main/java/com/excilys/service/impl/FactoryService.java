package com.excilys.service.impl;

import java.sql.ResultSet;

import com.excilys.dao.impl.DAOFactory;

import java.sql.Connection;

import com.mysql.jdbc.PreparedStatement;

public enum FactoryService {
	INSTANCE;
	
	private DAOFactory daoFactory = DAOFactory.INSTANCE;

	public Connection getConnection() {
		return daoFactory.getConnection();
	}
	
	public void safeClose(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
		daoFactory.safeClose(connection, preparedStatement, resultSet);
	}
}
