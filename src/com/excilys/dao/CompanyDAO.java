package com.excilys.dao;

import com.mysql.jdbc.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.om.Company;
import com.mysql.jdbc.Statement;

public class CompanyDAO {

	DAOFactory daoFactory = DAOFactory.getInstance();
		
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
		daoFactory.executeSQLQuery(sql);
	}
	
	/**
	 * 
	 * @return ResulSet of Company
	 */
	public Company findById(int id) {
		Connection connection = null;
		Statement statement = null;
		Company company = new Company();
		ResultSet queryResult = null;
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM company WHERE company.id=").
				append(id);
		try {
			connection = daoFactory.getConnection();
			statement = (Statement) connection.createStatement();
			queryResult = statement.executeQuery(builder.toString());
			while (queryResult.next()) {
				company.setId(id);
				company.setName(queryResult.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOFactory.safeClose(connection, statement, queryResult);
		}
		return company;
	}
	
	public List<Company> findAll() {
		Connection connection = null;
		Statement statement = null;
		List<Company> companies = new ArrayList<Company>();
		ResultSet queryResult = null;
		String sql = "SELECT id, name FROM company ;";
		try {
			connection = daoFactory.getConnection();
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
		daoFactory.executeSQLQuery(sql);
	}
	
	public void delete(int companyIdToDelete) {
		StringBuilder builder = new StringBuilder();
		String sql = builder.append("DELETE FROM company WHERE company.id=").
				append(companyIdToDelete).
				append (";").
				toString();
		daoFactory.executeSQLQuery(sql);
	}
}
