package com.excilys.dao;

import java.util.List;

import com.excilys.om.Computer;

public interface IComputerDAO {

	/**
	 * delete the computer with the selected id
	 * @param id
	 */
	public void delete (int id);
	
	/**
	 * find a computer by its Id
	 * @param id
	 * @return Computer
	 */
	public Computer findById (int id);
	
	/**
	 * Find a list of computer according to the parameters given
	 * @param numPage 			-> the current Page number
	 * @param entitiesPerPage	-> the number of entities to show in the page
	 * @param filter			-> the filter of search (if exists)
	 * @param order				-> the order of the list (asc or desc)
	 * @param criteria			-> the criteria to order (computer's name or company's name)
	 * @return List
	 */
	public List<Computer> findInPage (int numPage, int entitiesPerPage, String filter, String order, String criteria);

	/**
	 * Count the number of computer according to a filter
	 * if filter = "" -> count all computers
	 * @param filter
	 * @return int
	 */
	public int count(String filter);
	
	/**
	 * Save a computer in the database
	 * If computer does not exist, it create the computer
	 * If computer already exists, it updates the computer
	 * @param computer
	 */
	public void save(Computer computer);
}
