package com.excilys.dao.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import ch.qos.logback.classic.Logger;

import com.excilys.dao.IComputerDAO;
import com.excilys.exceptions.IllegalPersonnalException;
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
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Computer> criteria = builder.createQuery(Computer.class );
		Root<Computer> computer = criteria.from(Computer.class );

		criteria.select(computer);
		criteria.where( builder.equal( computer.get("id"), id ) );
		return em.createQuery(criteria).getResultList().get(0);
	}
		
	@SuppressWarnings("unchecked")
	public List<Computer> getInPage (int numPage, int entitiesPerPage, String filter, String order, String criteria) {
		logger.info("trying to find a list of computer according to several criteria");
		
		String orderSQL = "asc";
		String criteriaSQL = "cp.name";
		boolean orderBool = false; //false = asc, true = desc
		if ("desc".equals(order)){
			orderBool = true;
		}
		if(orderBool){
			orderSQL = "desc";
		}
		if("company".equals(criteria)) {
			criteriaSQL = "c.name";
		}
		String hql = "select cp "
				+ "from Computer cp "
				+ "left join cp.company c "
				+ "where cp.name like :filter "
				+ "order by "+criteriaSQL +" " +orderSQL+" ";

		try {
			int firstResult = ((numPage-1)*entitiesPerPage);
			Query query = em.createQuery(hql);
			query.setParameter("filter", "%"+filter+"%");
			query.setFirstResult(firstResult);
			query.setMaxResults(entitiesPerPage);
			logger.info("loading the list is complete");
			return query.getResultList();
		} catch (DataAccessException e) {
			logger.debug("failed to load the list of computer "+e.getMessage());
			throw new IllegalPersonnalException();
		}
	}

	public long count(String filter) {
		logger.info("attempting to count the number of computer with a filter");
		StringBuilder builder = new StringBuilder();
		String hql = "";
		if (filter.isEmpty()){ //count all computer
			hql = builder.append("select count(*) from Computer cp").toString();
		} else { //count searching computer
			hql = builder.append("select count(*) from Computer cp where cp.name like '%").
					append(filter).
					append("%'").toString();
		}
		
		try {
			Query query = em.createQuery(hql);
			return (long) query.getResultList().get(0);
		} catch (DataAccessException e) {
			logger.debug("failed to count...such a shame ..... "+e.getMessage());
			throw new IllegalPersonnalException();
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
