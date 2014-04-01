package com.excilys.service;

import java.util.List;

import com.excilys.om.Computer;
import com.excilys.dao.ComputerDAO;
import com.excilys.dao.DAOFactory;
import com.excilys.dao.LogDAO;
import com.excilys.exceptions.IllegalPersonnalException;

public enum ComputerService {
	
	INSTANCE;
	
	private ComputerDAO computerDAO = ComputerDAO.INSTANCE;
	private LogDAO logDao = LogDAO.INSTANCE;
	
	public void delete(int id) {
		try {
			DAOFactory.INSTANCE.startTransaction();
			computerDAO.delete(id);
			StringBuilder logB = new StringBuilder();
			logB.append("computer (id=").
				append(id).
				append(") was deleted from the database");
			logDao.create(logB.toString());
			DAOFactory.INSTANCE.commit();
		} catch (Exception e) {
			DAOFactory.INSTANCE.rollback();
			throw new IllegalPersonnalException();
		} finally {
			DAOFactory.INSTANCE.closeConnection();
		}
	}
	
	public Computer findById(int id) {
		return computerDAO.findById(id);
	}
	
	public List<Computer> findInPage (int numPage, int entitiesPerPage, String filter, String order, String criteria) {
		return computerDAO.findInPage(numPage, entitiesPerPage, filter, order, criteria);
	}
	
	public int count(String filter) {
		try {
			return computerDAO.count(filter);
		} catch (IllegalPersonnalException e) {
			throw new IllegalPersonnalException();
		}
	}

	public void save(Computer computer) {
		try {
			DAOFactory.INSTANCE.startTransaction();
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
			DAOFactory.INSTANCE.commit();
		} catch (Exception e) {
			DAOFactory.INSTANCE.rollback();
			throw new IllegalPersonnalException();
		} finally {
			DAOFactory.INSTANCE.closeConnection();
		}
	}
}