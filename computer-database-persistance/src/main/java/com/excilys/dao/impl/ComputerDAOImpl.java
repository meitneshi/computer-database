package com.excilys.dao.impl;


import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ch.qos.logback.classic.Logger;

import com.excilys.dao.IComputerDAO;
import com.excilys.exceptions.IllegalPersonnalException;
import com.excilys.mapper.ComputerRowMapper;
import com.excilys.om.Computer;
import com.jolbox.bonecp.BoneCPDataSource;

@Repository
public class ComputerDAOImpl implements IComputerDAO{
	
	public ComputerDAOImpl() {
		super();
	}

	@Autowired
	private BoneCPDataSource dataSource;
	
	private final static Logger logger = (Logger) LoggerFactory.getLogger(ComputerDAOImpl.class);
	
	public void delete(int computerIdToDelete) {
		logger.info("trying to delete a computer");
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		String sql = "DELETE FROM computer WHERE computer.id= ?";
		try {
			jt.update(sql, new Object[] { computerIdToDelete });
			logger.info("delete computer is successfull");
		} catch (DataAccessException e) {
			logger.debug("failed to delete computer "+e.getMessage());
			throw new DataAccessResourceFailureException(e.getMessage());
		}
	}
	
	public Computer getById (int id) {
		logger.info("trying to find a computer by id");
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		String sql = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
				+ "FROM computer "
				+ "LEFT JOIN company ON computer.company_id = company.id "
				+ "WHERE computer.id = ?";
		
		try {
			Computer computer = ((JdbcTemplate) jt).queryForObject(
					sql, new Object[] { id }, new ComputerRowMapper());
			logger.info("computer was found");
			return computer;
		} catch (DataAccessException e) {
			logger.debug("failed to find a computer by id "+e.getMessage());
			throw new IllegalPersonnalException();
		}
	}
		
	public List<Computer> getInPage (int numPage, int entitiesPerPage, String filter, String order, String criteria) {
		logger.info("trying to find a list of computer according to several criteria");
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		
		String orderSQL = "asc";
		String criteriaSQL = "computer.name";
		boolean orderBool = false; //false = asc, true = desc
		if ("desc".equals(order)){
			orderBool = true;
		}
		if(orderBool){
			orderSQL = "desc";
		}
		if("company".equals(criteria)) {
			criteriaSQL = "company.name";
		}
		
		String sql = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
				+ "FROM computer "
				+ "LEFT JOIN company "
				+ "ON computer.company_id = company.id WHERE computer.name LIKE ? "
				+ "ORDER BY " + criteriaSQL + " " + orderSQL + " "
				+ "LIMIT ?, ? ";
		
		try {
			List<Computer> computers = jt.query(sql,
		            new Object[] { "%"+filter+"%", ((numPage-1)*entitiesPerPage), entitiesPerPage }, new ComputerRowMapper());
			logger.info("loading the list is complete");
			return computers;
		} catch (DataAccessException e) {
			logger.debug("failed to load the list of computer "+e.getMessage());
			throw new IllegalPersonnalException();
		}
	}

	public int count(String filter) {
		logger.info("attempting to count the number of computer with a filter");
		int numberFinal = 0;
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		StringBuilder builder = new StringBuilder();
		String sql = "";
		if ("".equals(filter)){ //count all computer
			sql = builder.append("SELECT COUNT(id) FROM computer;").toString();
		} else { //count searching computer
			sql = builder.append("SELECT COUNT(id) FROM computer WHERE name LIKE '%").
					append(filter).
					append("%';").toString();
		}
		
		try {
			numberFinal = jt.queryForObject(sql, Integer.class);
			logger.info("count is successfull....");
			return numberFinal;
		} catch (DataAccessException e) {
			logger.debug("failed to count...such a shame ..... "+e.getMessage());
			throw new IllegalPersonnalException();
		}
	}

	public void save(Computer computer) {
		logger.info("attempting to save a computer");
		JdbcTemplate jt = new JdbcTemplate(dataSource);
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
			jt.update(sql, new Object [] { id, name, introducedL, discontinuedL, companyId, name, introducedL, discontinuedL, companyId });
			logger.info("save is successfull");	
		} catch (DataAccessException e) {
			logger.debug("failed to save the computer "+e.getMessage());
			throw new IllegalPersonnalException();
		}
	}
}
