package com.excilys.dao;

import java.util.List;

import com.excilys.om.Company;

public interface ICompanyDAO {
	
	/**
	 * Find a company by its Id
	 * @param id
	 * @return Company
	 */
	public Company findById(long id);
	
	/**
	 * return the full list of company
	 * @return List
	 */
	public List<Company> findAll();

	/**
	 * Function to initialize a company with the id given in parameter
	 * @param companyId
	 * @return Company
	 */
	public Company initCompany(long id);
}
