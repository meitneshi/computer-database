package com.excilys.dao;

import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.domainClass.Company;
import com.mysql.jdbc.Statement;

public class CompanyDAO {

	DAOFactory connectionManager = DAOFactory.getInstance();
		
	private final static CompanyDAO _instance = new CompanyDAO();
	
	
	public static CompanyDAO getInstance() {
		return _instance;
	}
	
	public CompanyDAO() {
		super();
	}
	
	public void create(Company companyToAdd) {
		StringBuilder builder = new StringBuilder();
		String sql = builder.append("INSERT INTO company (id, name) VALUES").
				append(" (null, '").
				append(companyToAdd.getName()).
				append("');").
				toString();
		this.executeSQLQuery(sql);
	}
	
	/**
	 * 
	 * @return ResulSet of Company
	 */
	public List<Company> find(Company companytofind) {
		Connection connection = null;
		Statement statement = null;
		List<Company> companiesResult = new ArrayList<Company>();
		ResultSet queryResult = null;
		StringBuilder builder = new StringBuilder();
		String sql = builder.append("SELECT * FROM company WHERE company.name LIKE '%").
				append(companytofind.getName()).
				append("%' ").
				append("OR company.id = ").
				append(companytofind.getId()).
				toString();
		try {
			connection = this.connectionManager.getConnection();
			statement = (Statement) connection.createStatement();
			queryResult = statement.executeQuery(sql);
			while (queryResult.next()) {
				Company company = new Company(queryResult.getString(2).toString(), queryResult.getInt(1));
				companiesResult.add(company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOFactory.safeClose(connection, statement, queryResult);
		}
		return companiesResult;
	}
	
	public List<Company> findAll() {
		Connection connection = null;
		Statement statement = null;
		List<Company> companies = new ArrayList<Company>();
		ResultSet queryResult = null;
		String sql = "SELECT id, name FROM company ;";
		try {
			connection = this.connectionManager.getConnection();
			statement = (Statement) connection.createStatement();
			queryResult = statement.executeQuery(sql);
			while (queryResult.next()) {
				Company comp = new Company(queryResult.getString(2), queryResult.getInt(1));
				companies.add(comp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOFactory.safeClose(connection, statement, queryResult);
		}
		return companies;
		
	}
	
	/**
	 * 
	 * for the moment user can only modify the name
	 * params[] look like [0 -> "name_enter_by_the_user"]
	 * 
	 * @param companytoUpdate
	 * @param params
	 */
	public void update(Company companytoUpdate, String[] params) {
		StringBuilder builder = new StringBuilder();
		String sql = builder.append("UPDATE company SET name=").
				append(params[0]).
				append(" WHERE ").
				append("company.id = ").
				append(companytoUpdate.getId()).
				append (";").
				toString();
		this.executeSQLQuery(sql);
	}
	
	public void delete(int companyIdToDelete) {
		StringBuilder builder = new StringBuilder();
		String sql = builder.append("DELETE FROM company WHERE company.id=").
				append(companyIdToDelete).
				append (";").
				toString();
		this.executeSQLQuery(sql);
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
}
