package com.excilys.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import ch.qos.logback.classic.Logger;

import com.excilys.dao.ICompanyDAO;
import com.excilys.exceptions.IllegalPersonnalException;
import com.excilys.om.Company;
import com.jolbox.bonecp.BoneCPDataSource;

@Repository
public class CompanyDAOImpl implements ICompanyDAO{

	private final static Logger logger = (Logger) LoggerFactory.getLogger(CompanyDAOImpl.class);
	
	public CompanyDAOImpl() {
		super();
	}
	
	@Autowired
	private BoneCPDataSource datasource;
	
	public Company findById(int id) {
		logger.info("attempting to find a company by id");
		Connection connection = DataSourceUtils.getConnection(datasource);
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
			throw new IllegalPersonnalException();
		} finally {
			this.safeClose(connection, preparedStatement, null);
		}
		return company;
	}
	
	public List<Company> findAll() {
		logger.info("attempting to find a company by id");
		Connection connection = DataSourceUtils.getConnection(datasource);
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
			throw new IllegalPersonnalException();
		} finally {
			this.safeClose(connection, preparedStatement, queryResult);
		}
		return companies;
	}

	public Company initCompany(String id) {
		Company company;
		int companyIdInt = 0;
		try {
			companyIdInt = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			logger.debug("failed to parseInt the company Id "+e.getMessage());
		}
		if (companyIdInt == 0) {
			company = new Company(null);
		} else {
			company = this.findById(companyIdInt);
		}
		return company;
	}
	
	public void safeClose(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
		logger.info("attempting to close safe");
		try {
			if (connection != null) {
				connection.close();
				logger.info("connection closed");
			}
			if (resultSet != null){
				resultSet.close();
				logger.info("resultSet closed");
			}
			if (preparedStatement != null) {
				preparedStatement.close();
				logger.info("presparedStatement closed");
			}
		} catch (SQLException e) {
			logger.debug("Safe Close failed "+e.getMessage());
			throw new IllegalPersonnalException();
		}
	}
}
