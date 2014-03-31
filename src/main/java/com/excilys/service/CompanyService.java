package com.excilys.service;

import java.util.List;

import com.excilys.dao.CompanyDAO;
import com.excilys.om.Company;

public enum CompanyService {

	INSTANCE;
	
	private CompanyDAO companyDAO = CompanyDAO.INSTANCE;
	
	public Company findById(int id) {
		return companyDAO.findById(id);
	}
	
	public List<Company> findAll() {
		return companyDAO.findAll();
	}
}
