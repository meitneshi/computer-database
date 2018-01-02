package com.excilys.service;

import java.util.List;

import com.excilys.om.Computer;
import com.excilys.wrapper.PageWrapper;

public interface IComputerService {
	
	/**
	 * delete the computer with the selected id
	 * @param id  the id
	 */
	public void delete (long id);
	
	/**
	 * find a computer by its Id
	 * @param id  the id
	 * @return Computer
	 */
	public Computer getById (long id);
	
	/**
	 * Find a list of computer according to the parameters given
	 * @param numPage 			 the current Page number
	 * @param entitiesPerPage	 the number of entities to show in the page
	 * @param filter			 the filter of search (if exists)
	 * @param order				 the order of the list (asc or desc)
	 * @param criteria			 the criteria to order (computer's name or company's name)
	 * @return List
	 */
	public List<Computer> getInPage (int numPage, int entitiesPerPage, String filter, String order, String criteria);

	/**
	 * return the entire list of computer
	 * @return List of computer
	 */
	public List<Computer> findAll();
	
	/**
	 * Count the number of computer according to a filter
	 * if filter = ""  count all computers
	 * @param filter the filter
	 * @return int
	 */
	public long count(String filter);
	
	/**
	 * Save a computer in the database
	 * If computer does not exist, it create the computer
	 * If computer already exists, it updates the computer
	 * @param computer the computer
	 */
	public void save(Computer computer);

	/**
	 * Generate the page to show
	 * @param page the page
	 * @param entitiesPerPage the number of entities per page
	 * @param filter the filter
	 * @param order the order
	 * @param criteria the criteria
	 * @param add the add
	 * @param edit the edit
	 * @param delete the delete
	 * @return PageWrapper.class
	 */
	public PageWrapper generatePage(String page, String entitiesPerPage,
			String filter, String order, String criteria, String add,
			String edit, String delete);
}