package com.excilys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.dao.ILogDAO;
import com.excilys.om.Computer;
import com.excilys.repositories.ComputerRepository;
import com.excilys.service.IComputerService;
import com.excilys.wrapper.PageWrapper;

@Service
public class ComputerServiceImpl implements IComputerService {

	public ComputerServiceImpl() {
		super();
	}

	@Autowired
	private ILogDAO logDao;
	@Autowired
	private ComputerRepository repository;

	@Transactional(readOnly = false)
	public void delete(long id) {
		repository.delete(id);
		StringBuilder logB = new StringBuilder();
		logB.append("computer (id=").append(id)
		.append(") was deleted from the database");
		logDao.create(logB.toString());
	}

	@Transactional(readOnly = true)
	public Computer getById(long id) {
		return repository.findOne(id);
	}

	@Transactional(readOnly = true)
	public List<Computer> getInPage(int numPage, int entitiesPerPage,
			String filter, String order, String criteria) {
		Sort sort = new Sort(Sort.Direction.ASC, "name");
		Pageable page = new PageRequest(numPage-1, entitiesPerPage, sort);
		return repository.findByNameContaining(filter, page);
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
		if (filter != null) {
			numberOfComputerDouble = (double) this.count(filter);
		}
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
		
		String pCriteria = "name";
		Sort sort = null;
		Direction direction = Sort.Direction.ASC;
		
		if ("company".equals(criteria)) {
			pCriteria = "companyName";
		} else { //default value
			pCriteria = "name";
		}
		page.setCriteria(criteria);
		
		if ("desc".equals(order)) {
			direction = Sort.Direction.DESC;
		}
		page.setOrder(order);
		sort = new Sort(direction, pCriteria);
		
		Pageable pageR = new PageRequest(numPage-1, entitiesPerPage, sort);
		
		if (filter != null) {
			page.setNumberOfComputer(this.count(filter));
			page.setFilter(filter);
			page.setComputerPageList(repository.findByNameContaining(filter, pageR));
		} else { // default
			page.setNumberOfComputer(this.count(""));
			page.setFilter("");
			page.setComputerPageList(repository.findByNameContaining("", pageR));
		}

		page.setNumberTotalOfComputer(this.count(""));
		page.setPageMax(pageMax);

		return page;
	}

	@Transactional(readOnly = false)
	public long count(String filter) {
		if (filter.isEmpty()) {
			return repository.count();
		} else {
			return repository.countByNameContaining("%"+filter+"%");
		}
	}

	@Transactional(readOnly = false)
	public void save(Computer computer) {
		repository.saveAndFlush(computer);
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