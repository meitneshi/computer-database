package com.excilys.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
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
	@Autowired
	private JdbcTemplate jt;
	
	@PersistenceContext
    private EntityManager em;
	
	public Company findById(int id) {
		logger.info("attempting to find a company by id");
		String hql = "from Company c where c.id = :compdId";
		try {
			Query query =  em.createQuery(hql);
			query.setParameter("compId", id);
		    return (Company) query.getResultList().get(0);
		} catch (DataAccessException e) {
			logger.debug("failed to found a company by id "+e.getMessage());
			throw new IllegalPersonnalException();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Company> findAll() {
		logger.info("attempting to find a company by id");
		String hql = "from Company";
		try {
			Query query = em.createQuery(hql);
			return query.getResultList();
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
