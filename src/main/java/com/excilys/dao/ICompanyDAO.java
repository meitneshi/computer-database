package com.excilys.dao;

import java.util.List;

import com.excilys.om.Company;

public interface ICompanyDAO {
	
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

}
