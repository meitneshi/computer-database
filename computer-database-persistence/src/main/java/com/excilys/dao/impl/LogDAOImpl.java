//package com.excilys.dao.impl;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import ch.qos.logback.classic.Logger;
//
//import com.excilys.dao.ILogDAO;
//import com.excilys.om.Log;
//import com.jolbox.bonecp.BoneCPDataSource;
//
//@Repository
//public class LogDAOImpl implements ILogDAO{
//
//	private final static Logger logger = (Logger) LoggerFactory.getLogger(LogDAOImpl.class);
//	
//	@Autowired
//	private BoneCPDataSource dataSource;
//	
//	@PersistenceContext(unitName="computerPersistenceUnit")
//    private EntityManager em;
//	
//	public void create(String logMessage) {
//		logger.info("Attempting to log in database");
//		Log log = new Log(logMessage);
//		em.persist(log);
//	}
//}
