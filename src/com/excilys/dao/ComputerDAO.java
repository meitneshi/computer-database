package com.excilys.dao;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.om.Company;
import com.excilys.om.Computer;


public class ComputerDAO {
	
	DAOFactory daoFactory = DAOFactory.getInstance();
	
	private final static ComputerDAO _instance = new ComputerDAO();	
	
	public static ComputerDAO getInstance() {
		return _instance;
	}
	
	public ComputerDAO() {
		super();
	}
	
	public void delete(int computerIdToDelete) {
		Connection connection = DAOFactory.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "DELETE FROM computer WHERE computer.id= ?";
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			preparedStatement.setInt(1, computerIdToDelete);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOFactory.safeClose(connection, preparedStatement, null);
		}
	}
	
	public Computer findById (int id) {
		Connection connection = DAOFactory.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet queryResult = null;
		Computer computerResult = null;
		String sql = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
				+ "FROM computer "
				+ "LEFT JOIN company ON computer.company_id = company.id "
				+ "WHERE computer.id = ?";
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			queryResult = preparedStatement.executeQuery();
			queryResult.next();
			Company company = new Company(queryResult.getString("company.name"), queryResult.getInt("company_id"));
			computerResult = new Computer(id, company, queryResult.getString("name"), queryResult.getDate("introduced"), queryResult.getDate("discontinued"));
			return computerResult;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DAOFactory.safeClose(connection, preparedStatement, null);
		}
		return computerResult;
	}
	
	//select X entities according to page number
		//order = ASC ou DESC
		//criteria = field to order by		
		public List<Computer> findInPage (int numPage, int entitiesPerPage, String filter, String order, String criteria) {
			Connection connection = DAOFactory.getInstance().getConnection();
			PreparedStatement preparedStatement = null;
			ResultSet queryResult = null;
			List<Computer> computers = new ArrayList<Computer>();
			String orderSQL = "asc";
			String criteriaSQL = "computer.name";
			boolean orderBool = false; //false = asc, true = desc
			
			if ("desc".equals(order)){
				orderBool = true;
			}
			if(orderBool){
				orderSQL = "desc";
			}
			if("company".equals(criteria)) {
				criteriaSQL = "company.name";
			}
			String sql = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
					+ "FROM computer "
					+ "LEFT JOIN company "
					+ "ON computer.company_id = company.id WHERE computer.name LIKE ? "
					+ "ORDER BY " + criteriaSQL + " " + orderSQL + " "
					+ "LIMIT ?, ? ";
			try {
				preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
				preparedStatement.setString(1, "%"+filter+"%");
				preparedStatement.setInt(2, ((numPage-1)*entitiesPerPage));
				preparedStatement.setInt(3, entitiesPerPage);
				System.out.println(preparedStatement);
				queryResult = preparedStatement.executeQuery();
				while(queryResult.next()) {
					Company company = new Company(queryResult.getString("company.name"), queryResult.getInt("company_id"));
					Computer computer = new Computer(queryResult.getInt("id"), company, queryResult.getString("name"), queryResult.getTimestamp("introduced"), queryResult.getTimestamp("discontinued"));
					computers.add(computer);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DAOFactory.safeClose(connection, preparedStatement, null);
			}
			return computers;
		}

	public int count(String filter) {
		int numberFinal = 0;
		ResultSet number = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		StringBuilder builder = new StringBuilder();
		String sql = "";
		if ("".equals(filter)){ //count all computer
			sql = builder.append("SELECT COUNT(id) FROM computer;").toString();
		} else { //count searching computer
			sql = builder.append("SELECT COUNT(id) FROM computer WHERE name LIKE '%").
					append(filter).
					append("%';").toString();
		}
		try {
			connection = daoFactory.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			number = preparedStatement.executeQuery();
			while (number.next()) {
				numberFinal = number.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOFactory.safeClose(connection, preparedStatement, null);
		}
		return numberFinal;
	}

	public void save(Computer computer) {
		Connection connection = DAOFactory.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO computer (id, name, introduced, discontinued, company_id) "
				+ "VALUES (?, ?, (FROM_UNIXTIME(?)), (FROM_UNIXTIME(?)), ?) "
				+ "ON DUPLICATE KEY UPDATE "
				+ "id=LAST_INSERT_ID(id), name=?, introduced=(FROM_UNIXTIME(?)), discontinued=(FROM_UNIXTIME(?)), company_id=?";
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			preparedStatement.setLong(1, computer.getId());
			preparedStatement.setString(2, computer.getName());
			preparedStatement.setString(6, computer.getName());
			if(computer.getIntroduced()==null) {
				preparedStatement.setDate(3, null);
				preparedStatement.setDate(7, null);
			} else {
				preparedStatement.setLong(3, computer.getIntroduced().getTime()/1000);
				preparedStatement.setLong(7, computer.getIntroduced().getTime()/1000);
			}
			if(computer.getDiscontinued() == null) {
				preparedStatement.setDate(4, null);
				preparedStatement.setDate(8, null);
			} else {
				preparedStatement.setLong(4, computer.getDiscontinued().getTime()/1000);
				preparedStatement.setLong(8, computer.getDiscontinued().getTime()/1000);
			}
			if(computer.getCompany().getId() == 0) {
				preparedStatement.setString(5, null);
				preparedStatement.setString(9, null);
			} else {
				preparedStatement.setLong(5, computer.getCompany().getId());
				preparedStatement.setLong(9, computer.getCompany().getId());
			}
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOFactory.safeClose(connection, preparedStatement, null);
		}
	}
}
