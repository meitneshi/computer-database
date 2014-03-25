package com.excilys.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.excilys.domainClass.Computer;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class ComputerPaginationDAO {

	DAOFactory connectionManager = DAOFactory.getInstance();
	
	private final static ComputerDAO _instance = new ComputerDAO();
	
	
	public static ComputerDAO getInstance() {
		return _instance;
	}
	
	public ComputerPaginationDAO() {
		// TODO Auto-generated constructor stub
	}
	
	//select X entities according to page number
	public List<Computer> findAllInPage (int numPage) {
		Connection connection = null;
		Statement statement = null;
		List<Computer> computers = new ArrayList<Computer>();
		ResultSet queryResult = null;
		StringBuilder builder = new StringBuilder();
		builder.append("");
		
		return computers;
	}

}
