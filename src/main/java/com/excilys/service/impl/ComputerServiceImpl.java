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