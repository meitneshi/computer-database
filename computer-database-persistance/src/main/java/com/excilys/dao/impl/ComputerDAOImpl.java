package com.excilys.dao.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
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
	@Autowired
	private JdbcTemplate jt;
	
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
		String hql = "select cp from Computer cp "
				+ "left join cp.company c "
				+ "where cp.id = :id";
		try {
			Query query = em.createQuery(hql, Computer.class);
			query.setParameter("id", id);
			logger.info("computer was found");
			return (Computer) query.getResultList().get(0);
		} catch (DataAccessException e) {
			logger.debug("failed to find a computer by id "+e.getMessage());
			throw new IllegalPersonnalException();
		}
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
			
			System.out.println(firstResult);
			System.out.println(entitiesPerPage);
			
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
		String sql = "INSERT INTO computer (id, name, introduced, discontinued, company_id) "
				+ "VALUES (?, ?, (FROM_UNIXTIME(?)), (FROM_UNIXTIME(?)), ?) "
				+ "ON DUPLICATE KEY UPDATE "
				+ "id=LAST_INSERT_ID(id), name=?, introduced=(FROM_UNIXTIME(?)), discontinued=(FROM_UNIXTIME(?)), company_id=?";
		
		Long id = computer.getId();
		String name = computer.getName();
		Long introducedL = (computer.getIntroduced() == null) ? null : computer.getIntroduced().getMillis()/1000;
		Long discontinuedL = (computer.getDiscontinued() == null) ? null : computer.getDiscontinued().getMillis()/1000;
		Long companyId = (computer.getCompany().getId() == 0) ? null : computer.getCompany().getId();		
		
		try {
//			em.persist(computer);
			jt.update(sql, new Object [] { id, name, introducedL, discontinuedL, companyId, name, introducedL, discontinuedL, companyId });
			logger.info("save is successfull");	
		} catch (DataAccessException e) {
			logger.debug("failed to save the computer "+e.getMessage());
			throw new IllegalPersonnalException();
		}
	}
}
