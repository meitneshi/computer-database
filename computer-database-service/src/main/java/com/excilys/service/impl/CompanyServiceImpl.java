package com.excilys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.dao.ICompanyDAO;
import com.excilys.om.Company;
import com.excilys.service.ICompanyService;

@Service
public class CompanyServiceImpl implements ICompanyService{

	@Autowired
	private ICompanyDAO companyDAO;
	
	public CompanyServiceImpl() {
		super();
	}
	
	@Transactional(readOnly = false)
	public Company findById(int id) {
		return companyDAO.findById(id);
	}
	
	@Transactional(readOnly = false)
	public List<Company> findAll() {
		return companyDAO.findAll();
	}

	@Transactional(readOnly = false)
	public Company initCompany(long id) {
		return companyDAO.initCompany(id);
	}
}
