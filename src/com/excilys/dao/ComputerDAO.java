package com.excilys.dao;

public class ComputerDAO {
	
	ConnectionManager connectionManager = ConnectionManager.getInstance();
	
	//Create Find Update Delete
	
	private final static ComputerDAO _instance = new ComputerDAO();
	
	public static ComputerDAO getInstance() {
		return _instance;
	}
	
	public ComputerDAO() {
	
	}

}
