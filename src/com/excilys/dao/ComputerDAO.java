package com.excilys.dao;


import com.mysql.jdbc.Connection;

import java.security.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	
	//Find Update

	public void create(Computer computerToAdd){
		System.out.println(computerToAdd);
		StringBuffer buffer = new StringBuffer();
		String sql = buffer.append("INSERT INTO computer (id, name, introduced, discontinued, company_id) VALUES").
				append(" (null, '").
				append(computerToAdd.getName()).
				append("', '").
				append(computerToAdd.getIntroduced()).
				append("', '").
				append(computerToAdd.getDiscontinued()).
				append("', '").
				append(computerToAdd.getCompany().getId()).
				append("');").
				toString();
		System.out.println(sql);
		this.executeSQLQuery(sql);
	}
	
	public void update(Computer computerToUpdate) {
		//TODO
	}
	
	public void delete(int computerIdToDelete) {
		StringBuffer buffer = new StringBuffer();
		String sql = buffer.append("DELETE FROM computer WHERE computer.id=").append(computerIdToDelete).append (";").toString();
		this.executeSQLQuery(sql);
	}
	
	public List<Computer> find (Computer computerTofind) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		List<Computer> computersResult = new ArrayList<Computer>();
		ResultSet queryResult = null;
		StringBuffer buffer = new StringBuffer();
		String sql = buffer.append("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name ").
				append("FROM computer ").
				append("RIGHT JOIN company ON computer.company_id = company.id ").
				append("WHERE computer.name LIKE '%").
				append(computerTofind.getName()).
				append("%';").
				toString();
		System.out.println(sql);
		try {
			connection = this.connectionManager.getConnection();
			statement = (Statement) connection.createStatement();
			queryResult = statement.executeQuery(sql);
			while (queryResult.next()) {
				Company company = new Company(queryResult.getString("company.name"), queryResult.getInt("company_id"));
				Computer computer = new Computer(
						queryResult.getInt("id"), 
						company, 
						queryResult.getString("name"), 
						queryResult.getTimestamp("introduced"), 
						queryResult.getTimestamp("discontinued"));
				computersResult.add(computer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOFactory.safeClose(connection, statement, queryResult);
		}
		return computersResult;
	}
	
	public List<Computer> findAll() {
		Connection connection = null;
		Statement statement = null;
		List<Computer> computers = new ArrayList<Computer>();
		ResultSet queryResult = null;
		String sql = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name FROM computer LEFT JOIN company ON computer.company_id = company.id;";
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
	
	//---test main----//
//	public static void main(String args[]) throws SQLException, ParseException {
//		ComputerDAO cdao = new ComputerDAO();
//		CompanyDAO codao = new CompanyDAO();
//		System.out.println("DAO créés");
//		
//		
//		Company company = new Company("patate");
//		Computer computer = new Computer(company, "mac", java.sql.Timestamp.valueOf("2012-12-12 00:00:00"), java.sql.Timestamp.valueOf("2012-12-12 00:00:00"));
//		System.out.println("computer a chercher créé");
				
//		Company c = new Company("rca");
//		List<Company> res = codao.find(c);
//		System.out.println("company Trouvé" + res);
//		
//		Company company = new Company(res.get(0).getName(), res.get(0).getId());
//		System.out.println("company créé pour ajout" + company);
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		
//		java.sql.Timestamp introduced = java.sql.Timestamp.valueOf("2012-12-12 00:00:00");
//		
//		java.sql.Timestamp discontinued = java.sql.Timestamp.valueOf("2012-12-13 00:00:00");		
//		
//		Computer comp = new Computer(company, "mon premier telephone", introduced, discontinued);
//		System.out.println("computer créé" + comp);
//		
//		cdao.create(comp);
//		System.out.println("computer crée en base");
//		List<Computer> res = cdao.find(computer);
//		for (Computer computers:res) {
//			System.out.println(computers);
//		}
//		List<Company> res = cdao.find(c);
//		cdao.create(c);
//		List<Computer> res = cdao.findAll();
//		for (Computer computer:res) {
//			System.out.println(computer);
//		}
//		cdao.delete(46);
//		System.out.println("c'est fait");
//	}

}
