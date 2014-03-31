package com.excilys.service;

import java.sql.SQLException;
import java.util.List;

import com.excilys.om.Computer;
import com.excilys.dao.ComputerDAO;
import com.excilys.dao.DAOFactory;

public enum ComputerService {
	
	INSTANCE;
	
	private ComputerDAO computerDAO = ComputerDAO.INSTANCE;
	
	public void delete(int id) {
		try {
			DAOFactory.INSTANCE.startTransaction();
			computerDAO.delete(id);
			DAOFactory.INSTANCE.commit();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			DAOFactory.INSTANCE.rollback();
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
		return computerDAO.count(filter);
	}

	public void save(Computer computer) {
		try {
			DAOFactory.INSTANCE.startTransaction();
			computerDAO.save(computer);
			DAOFactory.INSTANCE.commit();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			DAOFactory.INSTANCE.rollback();
		} finally {
			DAOFactory.INSTANCE.closeConnection();
		}
	}
}