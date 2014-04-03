package com.excilys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.dao.impl.ComputerDAOImpl;
import com.excilys.dao.impl.ConnectionFactory;
import com.excilys.dao.impl.LogDAOImpl;
import com.excilys.exceptions.IllegalPersonnalException;
import com.excilys.om.Computer;
import com.excilys.service.IComputerService;
import com.excilys.wrapper.PageWrapper;

@Service
public class ComputerServiceImpl implements IComputerService {
	
	public ComputerServiceImpl() {
		super();
	}
	
	@Autowired
	private ComputerDAOImpl computerDAO;
	
	@Autowired
	private ConnectionFactory daoFactory;
	
	@Autowired
	private LogDAOImpl logDao;
	
	public void delete(int id) {
		try {
			daoFactory.startTransaction();
			computerDAO.delete(id);
			StringBuilder logB = new StringBuilder();
			logB.append("computer (id=").
				append(id).
				append(") was deleted from the database");
			logDao.create(logB.toString());
			daoFactory.commit();
		} catch (IllegalPersonnalException e) {
			daoFactory.rollback();
			throw e;
		} finally {
			daoFactory.closeConnection();
		}
	}
	
	public Computer findById(int id) {
		return computerDAO.findById(id);
	}
	
	public List<Computer> findInPage (int numPage, int entitiesPerPage, String filter, String order, String criteria) {
		return computerDAO.findInPage(numPage, entitiesPerPage, filter, order, criteria);
	}
	
	public PageWrapper generatePage(
			String numPageS, 
			String entitiesPerPageS, 
			String filter, 
			String order, 
			String criteria) {
		PageWrapper page = new PageWrapper();
		
		int entitiesPerPage = 30;
		if (entitiesPerPageS != null) {
			entitiesPerPage = Integer.parseInt(entitiesPerPageS);
		}
		int numPage = 1;
		if (numPageS != null) {
			numPage = Integer.parseInt(numPageS);
		}
		
		double entitiesPerPageDouble = (double) entitiesPerPage;
		double numberOfComputerDouble = (double) this.count("");
		double pageMaxDouble = numberOfComputerDouble/entitiesPerPageDouble;
		int pageMax = (int) Math.ceil(pageMaxDouble);
		
		if (entitiesPerPageS != null) {
			page.setEntitiesPerPage(entitiesPerPage);
		} else {
			page.setEntitiesPerPage(30); //default
		}
		
		if (numPageS != null) {
			page.setCurrentPagenumber(numPage);
		} else {
			page.setCurrentPagenumber(1); //default
		}
		
		if (criteria != null) {
			page.setCriteria(criteria);
		} else {
			page.setCriteria("name");
		}
		
		if (order != null) {
			page.setOrder(order);
		} else {
			page.setOrder("asc");
		}
		
		if (filter != null) {
			page.setNumberOfComputer(this.count(filter));
			page.setFilter(filter);
			page.setComputerPageList(computerDAO.findInPage(numPage, entitiesPerPage, filter, order, criteria));
		} else { //default
			page.setNumberOfComputer(this.count(""));
			page.setFilter("");
			page.setComputerPageList(computerDAO.findInPage(numPage, entitiesPerPage, "", order, criteria));
		}
		
		page.setNumberTotalOfComputer(this.count(""));
		page.setPageMax(pageMax);
		
		return page;
	}
	
	public int count(String filter) {
		try {
			return computerDAO.count(filter);
		} catch (IllegalPersonnalException e) {
			throw e;
		}
	}

	public void save(Computer computer) {
		try {
			daoFactory.startTransaction();
			computerDAO.save(computer);
			StringBuilder logB = new StringBuilder();
			if (computer.getId() == 0) {
				logB.append("new computer create in database");
			} else {
				logB.append("computer (id=").
				append(String.valueOf(computer.getId())).
				append(") was edited in database");
			}
			logDao.create(logB.toString());
			daoFactory.commit();
		} catch (IllegalPersonnalException e) {
			daoFactory.rollback();
			throw e;
		} finally {
			daoFactory.closeConnection();
		}
	}
}