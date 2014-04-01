package com.excilys.service.impl;

import java.util.List;

import com.excilys.dao.impl.CompanyDAOImpl;
import com.excilys.om.Company;
import com.excilys.service.ICompanyService;

public enum CompanyServiceImpl implements ICompanyService{

	INSTANCE;
	
	private CompanyDAOImpl companyDAO = CompanyDAOImpl.INSTANCE;
	
	public Company findById(int id) {
		return companyDAO.findById(id);
	}
	
	public List<Company> findAll() {
		return companyDAO.findAll();
	}

	public Company initCompany(String id) {
		return companyDAO.initCompany(id);
	}
}
