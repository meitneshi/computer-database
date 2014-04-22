package com.excilys.dao.impl;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ch.qos.logback.classic.Logger;

import com.excilys.dao.ICompanyDAO;
import com.excilys.exceptions.IllegalPersonnalException;
import com.excilys.mapper.CompanyRowMapper;
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
	@Autowired
	private JdbcTemplate jt;
	
	public Company findById(int id) {
		logger.info("attempting to find a company by id");
		String sql = "SELECT * FROM company WHERE company.id = ?";
		try {
			Company company = jt.query(sql, new Object[] { id }, new CompanyRowMapper()).get(0);
			return company;
		} catch (DataAccessException e) {
			logger.debug("failed to found a company by id "+e.getMessage());
			throw new IllegalPersonnalException();
		}
	}
	
	public List<Company> findAll() {
		logger.info("attempting to find a company by id");
//		JdbcTemplate jt = new JdbcTemplate(datasource);
		String sql = "SELECT id, name FROM company ;";
		try {
			List<Company> companies = jt.query(sql, new CompanyRowMapper());
			logger.info("list of companies found");
			return companies;
		} catch (DataAccessException e) {
			logger.debug("failed to find the list of companies "+e.getMessage());
			throw new IllegalPersonnalException();
		}
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
}
