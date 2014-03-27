package com.excilys.dao;


import com.mysql.jdbc.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.om.Company;
import com.excilys.om.Computer;
import com.mysql.jdbc.Statement;


public class ComputerDAO {
	
	DAOFactory daoFactory = DAOFactory.getInstance();
	
	private final static ComputerDAO _instance = new ComputerDAO();
	
	
	public static ComputerDAO getInstance() {
		return _instance;
	}
	
	public ComputerDAO() {
		super();
	}
	
	public void create(Computer computerToAdd){
//		System.out.println(computerToAdd);
		StringBuilder builder = new StringBuilder();
		System.out.println(computerToAdd.getDiscontinued());
		builder.append("INSERT INTO computer (id, name, introduced, discontinued, company_id) VALUES").
				append(" (null, '").
				append(computerToAdd.getName()).
				append("', ");
		if(computerToAdd.getIntroduced()==null) {
			builder.append("null, ");
		} else {
			builder.append("(FROM_UNIXTIME(").append(computerToAdd.getIntroduced().getTime()/1000).append(")), ");
		}
		if(computerToAdd.getDiscontinued() == null) {
			builder.append("null, ");
		} else {
			builder.append("(FROM_UNIXTIME(").append(computerToAdd.getDiscontinued().getTime()/1000).append(")), ");
		}
		
		if (computerToAdd.getCompany().getId() == 0) {
			builder.append("null);");
		} else {
			builder.append("'").append(computerToAdd.getCompany().getId()).append("');");
		}
		System.out.println(builder.toString());
		daoFactory.executeSQLQuery(builder.toString());
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
		daoFactory.executeSQLQuery(builder.toString());
	}
	
	public void delete(int computerIdToDelete) {
		StringBuilder builder = new StringBuilder();
		String sql = builder.append("DELETE FROM computer WHERE computer.id=").append(computerIdToDelete).append (";").toString();
		daoFactory.executeSQLQuery(sql);
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
			connection = daoFactory.getConnection();
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
		public List<Computer> findInPage (int numPage, int entitiesPerPage, String filter, String order, String criteria) {
			Connection connection = null;
			Statement statement = null;
			List<Computer> computers = new ArrayList<Computer>();
			ResultSet queryResult = null;
			int offsetSQL = ((numPage-1)*entitiesPerPage);
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
					+ "FROM computer "
					+ "LEFT JOIN company "
					+ "ON computer.company_id = company.id ");
			builder.append("WHERE computer.name LIKE '%").append(filter).append("%' ");
			builder.append("ORDER BY ");
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
				connection = daoFactory.getConnection();
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
			connection = daoFactory.getConnection();
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
