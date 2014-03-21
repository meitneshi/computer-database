package com.excilys.dao;

import java.sql.Connection;
import java.util.List;

import com.excilys.domainClass.Computer;
import com.mysql.jdbc.Statement;

public class ComputerDAO {
	
	
	DAOFactory connectionManager = DAOFactory.getInstance();
	
	private final static ComputerDAO _instance = new ComputerDAO();
	private Connection connection;
	private Statement statement;
	
	
	public static ComputerDAO getInstance() {
		return _instance;
	}
	
	public ComputerDAO() {
		super();
	}
	
	//Create Find Findall Update Delete

	public void create(Computer computerToAdd){
		
	}
	
	public void update(Computer computerToUpdate) {
		
	}
	
	public void delete(int computerIdToDelete) {
		
	}
	
	public List<Computer> find (Computer computerTofind) {
		
		return null;
	}
	
	public List<Computer> findAll() {
		
		return null;
	}

}
