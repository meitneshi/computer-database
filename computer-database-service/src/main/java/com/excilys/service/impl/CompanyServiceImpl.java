package com.excilys.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.qos.logback.classic.Logger;

import com.excilys.om.Company;
import com.excilys.repositories.CompanyRepository;
import com.excilys.service.ICompanyService;

@Service
public class CompanyServiceImpl implements ICompanyService{
	
	private final static Logger logger = (Logger) LoggerFactory.getLogger(CompanyServiceImpl.class);

//	@Autowired
//	private ICompanyDAO companyDAO;
	@Autowired
	private CompanyRepository repository;
	@PersistenceContext
    private EntityManager em;
	
	public CompanyServiceImpl() {
		super();
	}
	
	@Transactional(readOnly = false)
	public Company findById(long id) {
		logger.info("attempting to find a company by id");
		return repository.findOne(id);
		
	}
	
	@Transactional(readOnly = false)
	public List<Company> findAll() {
		logger.info("attempting to find a company by id");
		return repository.findAll();
	}

	@Transactional(readOnly = false)
	public Company initCompany(long id) {
		Company company;
		if (id == 0) {
			company = new Company(null);
		} else {
			company = this.findById(id);
		}
		return company;
	}
}
