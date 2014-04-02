package com.excilys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.dao.impl.CompanyDAOImpl;
import com.excilys.om.Company;
import com.excilys.service.ICompanyService;

@Service
public class CompanyServiceImpl implements ICompanyService{

	public CompanyServiceImpl() {
		super();
	}
	
	@Autowired
	private CompanyDAOImpl companyDAO;
	
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
