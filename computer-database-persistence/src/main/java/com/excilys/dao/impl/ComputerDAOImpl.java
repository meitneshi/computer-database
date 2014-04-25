package com.excilys.dao.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.qos.logback.classic.Logger;

import com.excilys.dao.IComputerDAO;
import com.excilys.om.Computer;
import com.excilys.om.QCompany;
import com.excilys.om.QComputer;
import com.jolbox.bonecp.BoneCPDataSource;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.path.StringPath;

@Repository
public class ComputerDAOImpl implements IComputerDAO{
	
	public ComputerDAOImpl() {
		super();
	}

	@Autowired
	private BoneCPDataSource dataSource;
	
	@PersistenceContext(unitName="computerPersistenceUnit")
    private EntityManager em;
	
	private final static Logger logger = (Logger) LoggerFactory.getLogger(ComputerDAOImpl.class);
	
	
	public void delete(long computerIdToDelete) {
		
		logger.info("trying to delete a computer");
		Computer computer = em.find(Computer.class, computerIdToDelete);
		em.remove(computer);	
	}
	
	public Computer getById (long id) {
		logger.info("trying to find a computer by id");
		return em.find(Computer.class, id);
	}
		
	public List<Computer> getInPage (int numPage, int entitiesPerPage, String filter, String order, String criteria) {
		logger.info("trying to find a list of computer according to several criteria");

		JPAQuery query = new JPAQuery (em);
		QComputer computer = QComputer.computer;
		QCompany company = QCompany.company;
		
		query.from(computer).leftJoin(computer.company, company);
		
		if(filter != null){
			query.where(computer.name.like("%"+filter+"%"));
		}
		
		StringPath path = computer.name;
		if("company".equals(criteria)) {
			path = company.name;
		}
		
		if("desc".equals(order)) { //order desc
			query.orderBy(path.desc());
		} else { //order asc
			query.orderBy(path.asc());
		}
		
		query.limit(entitiesPerPage);
		query.offset(((numPage-1)*entitiesPerPage));
		
		return query.list(computer);
	}

	public long count(String filter) {
		
		JPAQuery query = new JPAQuery (em);
		QComputer computer = QComputer.computer;
		QCompany company = QCompany.company;
		query.from(computer).leftJoin(computer.company, company);
		
		if (filter.isEmpty()) {//count all computers
			return query.list(computer).size();
		} else { //count according to filter
			query.where(computer.name.like("%"+filter+"%"));
			return query.list(computer).size();
		}
	}

	public void save(Computer computer) {
		logger.info("attempting to save a computer");
		
		Long id = (computer.getId() == 0) ? null : computer.getId();
		if (id == null){ //new computer, create
			em.persist(computer);
		} else { //existing computer, edit
			em.merge(computer);
		}
	}
}
