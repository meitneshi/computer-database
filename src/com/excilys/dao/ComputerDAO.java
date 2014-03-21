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

import javax.print.attribute.standard.DateTimeAtCompleted;

import com.excilys.domainClass.Company;
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
		
	}
	
	public void delete(int computerIdToDelete) {
		StringBuffer buffer = new StringBuffer();
		String sql = buffer.append("DELETE FROM computer WHERE computer.id=").append(computerIdToDelete).append (";").toString();
		this.executeSQLQuery(sql);
	}
	
	public List<Computer> find (Computer computerTofind) {
		List<Computer> computerResult = new ArrayList<Computer>();
		ResultSet queryResult = null;
		StringBuffer buffer = new StringBuffer();
		String sql = buffer.append("SELECT * FROM computer WHERE computer.name LIKE '%").
				append(computerTofind.getName()).
				append("%'").
				toString();
//		try {
//			this.connection = this.connectionManager.getConnection();
//			this.statement = (Statement) connection.createStatement();
//			queryResult = statement.executeQuery(sql);
//			while (queryResult.next()) {
//				Computer computer = new Computer(
//						queryResult.getInt("id"),
//						queryResult.getString("name"),
//						queryResult.getDate("introduced"),
//						queryResult.getDate("discontinued"),
//						queryResult.getInt("company-id");
//				companiesResult.add(company);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			DAOFactory.safeClose(connection, statement, queryResult);
//		}
//		return companiesResult;
		return null;
	}
	
	public List<Computer> findAll() {
		List<Computer> companies = new ArrayList<Computer>();
		ResultSet queryResult = null;
		String sql = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name FROM computer INNER JOIN company ON computer.company_id = company.id;";
//		try {
//			this.connection = this.connectionManager.getConnection();
//			this.statement = (Statement) connection.createStatement();
//			queryResult = statement.executeQuery(sql);
//			while (queryResult.next()) {
//				Computer computer = new Computer(queryResult.getInt("id"), company, name, introduced, discontinued)
//				companies.add(comp);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			DAOFactory.safeClose(connection, statement, queryResult);
//		}
//		return companies;
		return null;
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
	public static void main(String args[]) throws SQLException, ParseException {
		ComputerDAO cdao = new ComputerDAO();
		CompanyDAO codao = new CompanyDAO();
		System.out.println("DAO créés");
//		
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
		cdao.delete(576);
//		List<Company> res = cdao.find(c);
//		cdao.create(c);
//		List<Company> res = cdao.findAll();
//		for (Company company:res) {
//			System.out.println(company);
//		}
//		cdao.delete(46);
		System.out.println("c'est fait");
	}

}
