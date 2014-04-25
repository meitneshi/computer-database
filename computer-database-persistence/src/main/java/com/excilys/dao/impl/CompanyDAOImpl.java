package com.excilys.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
	
	@PersistenceContext
    private EntityManager em;
	
	public Company findById(long id) {
		logger.info("attempting to find a company by id");
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Company> criteria = builder.createQuery(Company.class );
		Root<Company> company = criteria.from(Company.class );

		criteria.select(company);
		criteria.where( builder.equal( company.get("id"), id ) );
		return em.createQuery(criteria).getResultList().get(0);
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
