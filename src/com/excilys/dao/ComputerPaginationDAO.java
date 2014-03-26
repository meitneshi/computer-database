package com.excilys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.domainClass.Company;
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
	public List<Computer> findAllInPage (int numPage, int entitiesPerPage) {
		Connection connection = null;
		Statement statement = null;
		List<Computer> computers = new ArrayList<Computer>();
		ResultSet queryResult = null;
		int offsetSQL = ((numPage-1)*entitiesPerPage);
		StringBuilder builder = new StringBuilder();
		String sql = builder.append("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
				+ "FROM computer "
				+ "LEFT JOIN company "
				+ "ON computer.company_id = company.id ").
				append("ORDER BY computer.id LIMIT ").
				append(entitiesPerPage).
				append(" OFFSET ").
				append(offsetSQL).toString();
		System.out.println(sql);
		try {
			connection = this.connectionManager.getConnection();
			statement = (Statement) connection.createStatement();
			queryResult = statement.executeQuery(sql);
			while (queryResult.next()) {
				Company company = new Company(queryResult.getString("company.name"), queryResult.getInt("company_id"));
				Computer computer = new Computer(queryResult.getInt("id"), company, queryResult.getString("name"), queryResult.getTimestamp("introduced"), queryResult.getTimestamp("discontinued"));
				computers.add(computer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOFactory.safeClose(connection, statement, queryResult);
		}
		
		
		return computers;
	}

}
