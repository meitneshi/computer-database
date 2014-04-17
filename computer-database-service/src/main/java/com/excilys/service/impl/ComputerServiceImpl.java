package com.excilys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.dao.IComputerDAO;
import com.excilys.dao.ILogDAO;
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
	private IComputerDAO computerDAO;
	@Autowired
	private ILogDAO logDao;

	@Transactional(readOnly = false)
	public void delete(int id) {
		computerDAO.delete(id);
		StringBuilder logB = new StringBuilder();
		logB.append("computer (id=").append(id)
				.append(") was deleted from the database");
		logDao.create(logB.toString());
	}

	@Transactional(readOnly = true)
	public Computer getById(int id) {
		return computerDAO.getById(id);
	}

	@Transactional(readOnly = true)
	public List<Computer> getInPage(int numPage, int entitiesPerPage,
			String filter, String order, String criteria) {
		return computerDAO.getInPage(numPage, entitiesPerPage, filter, order,
				criteria);
	}

	@Transactional(readOnly = true)
	public PageWrapper generatePage(String numPageS, String entitiesPerPageS,
			String filter, String order, String criteria, String add,
			String edit, String delete) {
		PageWrapper page = new PageWrapper();

		page.setAdd(add);
		page.setEdit(edit);
		page.setDelete(delete);

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
		double pageMaxDouble = numberOfComputerDouble / entitiesPerPageDouble;
		int pageMax = (int) Math.ceil(pageMaxDouble);

		if (entitiesPerPageS != null) {
			page.setEntitiesPerPage(entitiesPerPage);
		} else {
			page.setEntitiesPerPage(30); // default
		}

		if (numPageS != null) {
			page.setCurrentPagenumber(numPage);
		} else {
			page.setCurrentPagenumber(1); // default
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
			page.setComputerPageList(computerDAO.getInPage(numPage,
					entitiesPerPage, filter, order, criteria));
		} else { // default
			page.setNumberOfComputer(this.count(""));
			page.setFilter("");
			page.setComputerPageList(computerDAO.getInPage(numPage,
					entitiesPerPage, "", order, criteria));
		}

		page.setNumberTotalOfComputer(this.count(""));
		page.setPageMax(pageMax);

		return page;
	}

	@Transactional(readOnly = false)
	public int count(String filter) {
		try {
			return computerDAO.count(filter);
		} catch (IllegalPersonnalException e) {
			throw e;
		}
	}

	@Transactional(readOnly = false)
	public void save(Computer computer) {
		computerDAO.save(computer);
		StringBuilder logB = new StringBuilder();
		if (computer.getId() == 0) {
			logB.append("new computer create in database");
		} else {
			logB.append("computer (id=")
					.append(String.valueOf(computer.getId()))
					.append(") was edited in database");
		}
		logDao.create(logB.toString());
	}
}