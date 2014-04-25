package com.excilys.dao.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import ch.qos.logback.classic.Logger;

import com.excilys.dao.IComputerDAO;
import com.excilys.exceptions.IllegalPersonnalException;
import com.excilys.om.Company;
import com.excilys.om.Computer;
import com.jolbox.bonecp.BoneCPDataSource;

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

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Computer> criteriaQuery = builder.createQuery(Computer.class);
		Root<Computer> computerRoot = criteriaQuery.from(Computer.class);
		Join<Computer, Company> company = computerRoot.join("company", JoinType.LEFT);

		if(filter != null){
			criteriaQuery.where(
					builder.like(computerRoot.<String>get("name"), "%"+filter+"%")
					);
		}
		
//		initialize path		
		Path<Object> path = computerRoot.get("name");
		if ("company".equals(criteria)) {
			path = company.get("name");
		}
		
//		create order by
		if("desc".equals(order)) { //order desc
			criteriaQuery.orderBy(builder.desc(path));
		} else { //order asc
			criteriaQuery.orderBy(builder.asc(path));
		}

		TypedQuery<Computer> query = em.createQuery(criteriaQuery);
		return query.setFirstResult(((numPage-1)*entitiesPerPage))
				.setMaxResults(entitiesPerPage)
				.getResultList();
	}

	public long count(String filter) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Computer> computerRoot = cq.from(Computer.class);
		
		if (filter.isEmpty()) {//count all computers
			cq.select(cb.count(computerRoot));
			return (Long)em.createQuery(cq).getSingleResult();
		} else { //count according to filter
			cq.select(cb.count(computerRoot));
			cq.where(
					cb.like(computerRoot.<String>get("name"), "%"+filter+"%")
					);
			return (Long)em.createQuery(cq).getSingleResult();
		}
	}

	public void save(Computer computer) {
		logger.info("attempting to save a computer");
		
		Long id = (computer.getId() == 0) ? null : computer.getId();
		try {
			if (id == null){ //new computer, create
				em.persist(computer);
			} else { //existing computer, edit
				em.merge(computer);
			}
			logger.info("save is successfull");	
		} catch (DataAccessException e) {
			logger.debug("failed to save the computer "+e.getMessage());
			throw new IllegalPersonnalException();
		}
	}
}
