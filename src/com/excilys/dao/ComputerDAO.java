package com.excilys.dao;


import com.mysql.jdbc.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.domainClass.Company;
import com.excilys.domainClass.Computer;
import com.mysql.jdbc.Statement;


public class ComputerDAO {
	
	DAOFactory connectionManager = DAOFactory.getInstance();
	
	private final static ComputerDAO _instance = new ComputerDAO();
	
	
	public static ComputerDAO getInstance() {
		return _instance;
	}
	
	public ComputerDAO() {
		super();
	}
	
	
//a refaire
	public void create(Computer computerToAdd){
		System.out.println(computerToAdd);
		StringBuilder builder = new StringBuilder();
		String sql = builder.append("INSERT INTO computer (id, name, introduced, discontinued, company_id) VALUES").
				append(" (null, '").
				append(computerToAdd.getName()).
				append("', '").toString();
		if (computerToAdd.getIntroduced() == null) {
			sql += "0000-00-00 00:00:00', ";
		} else {
			sql += computerToAdd.getIntroduced() + "', '";
		}
		if (computerToAdd.getDiscontinued() == null) {
			sql += "'0000-00-00 00:00:00', ";
		} else {
			sql += computerToAdd.getDiscontinued() + "', ";
		}
		if (computerToAdd.getCompany().getId() == 0) {
			sql += "null);";
		} else {
			sql += "'" + computerToAdd.getCompany().getId() + "');";
		}
		this.executeSQLQuery(builder.toString());
	}
	
	public void update(Computer computerToUpdate) {
		//initailisation
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE computer SET name = '").
				append(computerToUpdate.getName()).
				append("', introduced = '").
				append(computerToUpdate.getIntroduced()).
				append("', discontinued = '").
				append(computerToUpdate.getDiscontinued()).
				append("', company_id = '").
				append(computerToUpdate.getCompany().getId()).
				append("' WHERE id= ").
				append(computerToUpdate.getId()).
				append(";");
		//update the computer
		this.executeSQLQuery(builder.toString());
	}
	
	public void delete(int computerIdToDelete) {
		StringBuilder builder = new StringBuilder();
		String sql = builder.append("DELETE FROM computer WHERE computer.id=").append(computerIdToDelete).append (";").toString();
		this.executeSQLQuery(sql);
	}
	
	public Computer findById (int id) {
		Connection connection = null;
		Statement statement = null;
		Computer computerResult = new Computer();
		ResultSet queryResult = null;
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name ").
				append("FROM computer ").
				append("LEFT JOIN company ON computer.company_id = company.id ").
				append("WHERE computer.id = ").
				append(id);
		try {
			connection = this.connectionManager.getConnection();
			statement = (Statement) connection.createStatement();
			queryResult = statement.executeQuery(builder.toString());
			while (queryResult.next()) {
				Company company = new Company(queryResult.getString("company.name"), queryResult.getInt("company_id"));
				computerResult.setId(id);
				computerResult.setName(queryResult.getString("name"));
				computerResult.setIntroduced(queryResult.getTimestamp("introduced"));
				computerResult.setDiscontinued(queryResult.getTimestamp("discontinued"));
				computerResult.setCompany(company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOFactory.safeClose(connection, statement, queryResult);
		}
		return computerResult;
	}
	
	//select X entities according to page number
		//order = ASC ou DESC
		//criteria = field to order by
		public List<Computer> findAllInPage (int numPage, int entitiesPerPage, String order, String criteria) {
			Connection connection = null;
			Statement statement = null;
			List<Computer> computers = new ArrayList<Computer>();
			ResultSet queryResult = null;
			int offsetSQL = ((numPage-1)*entitiesPerPage);
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
					+ "FROM computer "
					+ "LEFT JOIN company "
					+ "ON computer.company_id = company.id "
					+ "ORDER BY ");
			if (criteria.equals("company")) { //field = company.name
				builder.append("company.name");
			} else { // field like computer.field
				builder.append("computer.").
				append(criteria);
			}
			builder.append(" ").
					append(order).
					append(" LIMIT ").
					append(entitiesPerPage).
					append(" OFFSET ").
					append(offsetSQL);
			try {
				connection = this.connectionManager.getConnection();
				statement = (Statement) connection.createStatement();
				queryResult = statement.executeQuery(builder.toString());
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
		
		public List<Computer> findSearchInPage (int numPage, int entitiesPerPage, String filter, String order, String criteria) {
			Connection connection = null;
			Statement statement = null;
			List<Computer> computers = new ArrayList<Computer>();
			ResultSet queryResult = null;
			int offsetSQL = ((numPage-1)*entitiesPerPage);
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
					+ "FROM computer "
					+ "LEFT JOIN company "
					+ "ON computer.company_id = company.id ").
					append("WHERE computer.name LIKE '%").
					append(filter).
					append("%' ").
					append("ORDER BY ");
			if ("company".equals(criteria)) { //field = company.name
				builder.append("company.name");
			} else { // field like computer.field
				builder.append("computer.").
				append(criteria);
			}
			builder.append(" ").
					append(order).
					append(" LIMIT ").
					append(entitiesPerPage).
					append(" OFFSET ").
					append(offsetSQL);
			try {
				connection = this.connectionManager.getConnection();
				statement = (Statement) connection.createStatement();
				queryResult = statement.executeQuery(builder.toString());
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
	
	public int executeSQLQuery(String sqlToExecute) {
		Connection connection = null;
		Statement statement = null;
		int result = 0;
		try {
			connection = this.connectionManager.getConnection();
			statement = (Statement) connection.createStatement();
			result = statement.executeUpdate(sqlToExecute);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOFactory.safeClose(connection, statement, null);
		}
		return result;
	}

	public int count(String filter) {
		int numberFinal = 0;
		ResultSet number = null;
		Connection connection = null;
		Statement statement = null;
		StringBuilder builder = new StringBuilder();
		String sql = "";
		if (filter.equals("")){ //count all computer
			sql = builder.append("SELECT COUNT(id) FROM computer;").toString();
		} else { //count searching computer
			sql = builder.append("SELECT COUNT(id) FROM computer WHERE name LIKE '%").
					append(filter).
					append("%';").toString();
		}
		try {
			connection = this.connectionManager.getConnection();
			statement = (Statement) connection.createStatement();
			number = statement.executeQuery(sql);
			while (number.next()) {
				numberFinal = number.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOFactory.safeClose(connection, statement, null);
		}
		return numberFinal;
	}
}
