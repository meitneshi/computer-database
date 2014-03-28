package com.excilys.service;

import java.util.List;

import com.excilys.dao.CompanyDAO;
import com.excilys.om.Company;

public class CompanyService {

	public CompanyService() {
		super();
	}
	
	private CompanyDAO companyDAO = CompanyDAO.getInstance();
	
	public Company findById(int id) {
		return CompanyDAO.findById(id);
	}
	
	public List<Company> findAll() {
		return companyDAO.findAll();
	}
}
