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
	private Connection connection;
	private Statement statement;
	
	
	public static CompanyDAO getInstance() {
		return _instance;
	}
	
	public CompanyDAO() {
		super();
	}
	
	public void create(Company companyToAdd) {
		StringBuffer buffer = new StringBuffer();
		String sql = buffer.append("INSERT INTO company (id, name) VALUES").
				append(" (null, '").
				append(companyToAdd.getName()).
				append("');").
				toString();
		this.executeSQLQuery(sql);
	}
	
	/**
	 * 
	 * company can only be search by name (for the moment)
	 * params[] look like [0 -> "name_enter_by_the_user"]
	 * 
	 * @param params
	 * @return ResulSet of Company
	 */
	public List<Company> find(Company companytofind) {
		List<Company> companiesResult = new ArrayList<Company>();
		ResultSet queryResult = null;
		StringBuffer buffer = new StringBuffer();
		String sql = buffer.append("SELECT * FROM company WHERE company.name LIKE '%").
				append(companytofind.getName()).
				append("%'").
				toString();
		try {
			this.connection = this.connectionManager.getConnection();
			this.statement = (Statement) connection.createStatement();
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
		List<Company> companies = new ArrayList<Company>();
		ResultSet queryResult = null;
		String sql = "SELECT id, name FROM company ;";
		try {
			this.connection = this.connectionManager.getConnection();
			this.statement = (Statement) connection.createStatement();
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
		StringBuffer buffer = new StringBuffer();
		String sql = buffer.append("UPDATE company SET name=").
				append(params[0]).
				append(" WHERE ").
				append("company.id = ").
				append(companytoUpdate.getId()).
				append (";").
				toString();
		this.executeSQLQuery(sql);
	}
	
	public void delete(int companyIdToDelete) {
		StringBuffer buffer = new StringBuffer();
		String sql = buffer.append("DELETE FROM company WHERE company.id=").
				append(companyIdToDelete).
				append (";").
				toString();
		this.executeSQLQuery(sql);
	}
	
	public int executeSQLQuery(String sqlToExecute) {
		int result = 0;
		try {
			this.connection = this.connectionManager.getConnection();
			this.statement = (Statement) connection.createStatement();
			result = statement.executeUpdate(sqlToExecute);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOFactory.safeClose(connection, statement, null);
		}
		return result;
	}
	
	//---test main----//
	public static void main(String args[]) throws SQLException {
		CompanyDAO cdao = new CompanyDAO();
		Company c = new Company("myapp");
		System.out.println(c);
		cdao.create(c);
		List<Company> res = cdao.find(c);
//		cdao.create(c);
//		List<Company> res = cdao.findAll();
		for (Company company:res) {
			System.out.println(company);
		}
//		cdao.delete(46);
		System.out.println("c'est fait");
	}
}
