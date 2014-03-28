package com.excilys.dao;

import ch.qos.logback.classic.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.excilys.om.Company;

public class CompanyDAO {

	static DAOFactory daoFactory = DAOFactory.getInstance();
		
	private final static CompanyDAO _instance = new CompanyDAO();
	private final static Logger logger = (Logger) LoggerFactory.getLogger(DAOFactory.class);
	
	public static CompanyDAO getInstance() {
		return _instance;
	}
	
	public CompanyDAO() {
		super();
	}
	
	/**
	 * 
	 * @return ResulSet of Company
	 */
	public static Company findById(int id) {
		logger.info("attempting to find a company by id");
		Connection connection = DAOFactory.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		Company company = null;
		ResultSet queryResult = null;
		String sql = "SELECT * FROM company WHERE company.id = ?";
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			queryResult = preparedStatement.executeQuery();
			queryResult.next();
			company = new Company(queryResult.getString("name"), id);
			logger.info("company found");
		} catch (SQLException e) {
			logger.debug("failed to found a company by id "+e.getMessage());
		} finally {
			DAOFactory.safeClose(connection, preparedStatement, null);
		}
		return company;
	}
	
	public List<Company> findAll() {
		logger.info("attempting to find a company by id");
		Connection connection = DAOFactory.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		List<Company> companies = new ArrayList<Company>();
		ResultSet queryResult = null;
		String sql = "SELECT id, name FROM company ;";
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			queryResult = preparedStatement.executeQuery(sql);
			while (queryResult.next()) {
				Company comp = new Company(queryResult.getString(2), queryResult.getInt(1));
				companies.add(comp);
			}
			logger.info("list of companies found");
		} catch (SQLException e) {
			logger.debug("failed to find the list of companies "+e.getMessage());
		} finally {
			DAOFactory.safeClose(connection, preparedStatement, queryResult);
		}
		return companies;
		
	}
}
