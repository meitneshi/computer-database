package com.excilys.service;

import java.util.List;

import com.excilys.om.Computer;
import com.excilys.dao.ComputerDAO;

public class ComputerService {
	
	public ComputerService() {
		super();
	}
	
	private ComputerDAO computerDAO = ComputerDAO.getInstance();
	
	public void delete(int id) {
		computerDAO.delete(id);
	}
	
	public Computer findById(int id) {
		return computerDAO.findById(id);
	}
	
	public List<Computer> findInPage (int numPage, int entitiesPerPage, String filter, String order, String criteria) {
		return computerDAO.findInPage(numPage, entitiesPerPage, filter, order, criteria);
	}
	
	public int count(String filter) {
		return computerDAO.count(filter);
	}

	public void save(Computer computer) {
		computerDAO.save(computer);
	}
}