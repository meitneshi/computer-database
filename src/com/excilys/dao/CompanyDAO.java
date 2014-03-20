package com.excilys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.domainClass.Company;

public class CompanyDAO {

	ConnectionManager connectionManager = ConnectionManager.getInstance();
	
	//Create Find Update Delete
	
	private final static CompanyDAO _instance = new CompanyDAO();
	
	public static CompanyDAO getInstance() {
		return _instance;
	}
	
	public CompanyDAO() {
		super();
	}
	
	public void create(Company companyToAdd) {
		StringBuffer buffer = new StringBuffer();
		String sql = buffer.append("INSERT INTO company (id, name) VALUES").append(" (null, '").append(companyToAdd.getName()).append("');").toString();
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
	public ResultSet find(String[] params) {
		StringBuffer buffer = new StringBuffer();
		String sql = buffer.append("SELECT * FROM company WHERE company.name LIKE '%").append(params[0]).append("%'").toString();
		ResultSet companiesResult = this.executeSQLQuery(sql);
		return companiesResult;
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
		String sql = buffer.append("UPDATE company SET name=").append(params[0]).append(" WHERE ").append("company.id = ").append(companytoUpdate.getId()).toString();
		this.executeSQLQuery(sql);
	}
	
	public void delete(Company companytoDelete) {
		StringBuffer buffer = new StringBuffer();
		String sql = buffer.append("DELETE FROM company WHERE company.id=").append(companytoDelete.getId()).append(" AND company.name=").append(companytoDelete.getName()).toString();
		this.executeSQLQuery(sql);
	}
	
	public ResultSet executeSQLQuery(String sqlToExecute) {
		ResultSet result = null;
		try {
			this.connectionManager.getConnection().createStatement().executeUpdate(sqlToExecute);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//---test main----//
	public static void main(String args[]) throws SQLException {
		CompanyDAO cdao = new CompanyDAO();
		Company myCompany = new Company("my First Company2");
		cdao.create(myCompany);
		System.out.println("c'est fait");
	}
}
