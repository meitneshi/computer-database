package com.excilys.service;

import java.util.List;

import com.excilys.om.Company;

public interface ICompanyService {

	/**
	 * Find a company by its Id
	 * @param id
	 * @return Company
	 */
	public Company findById(int id);
	
	/**
	 * return the full list of company
	 * @return List
	 */
	public List<Company> findAll();
	
	/**
	 * Function to initialize a company with the id given in parameter
	 * @param id
	 * @return Company
	 */
	public Company initCompany(String id);
}
