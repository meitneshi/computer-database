//package com.excilys.dao.impl;
//
//import java.util.List;
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
//import com.excilys.dao.ICompanyDAO;
//import com.excilys.om.Company;
//import com.excilys.om.QCompany;
//import com.jolbox.bonecp.BoneCPDataSource;
//import com.mysema.query.jpa.impl.JPAQuery;
//
//@Repository
//public class CompanyDAOImpl implements ICompanyDAO{
//
//	private final static Logger logger = (Logger) LoggerFactory.getLogger(CompanyDAOImpl.class);
//	
//	public CompanyDAOImpl() {
//		super();
//	}
//	
//	@Autowired
//	private BoneCPDataSource datasource;
//	@PersistenceContext
//    private EntityManager em;
//	
//	public Company findById(long id) {
//		logger.info("attempting to find a company by id");
//		return em.find(Company.class, id);
//	}
//	
//	public List<Company> findAll() {
//		logger.info("attempting to find a company by id");
//		JPAQuery query = new JPAQuery(em);
//		QCompany company = QCompany.company;
//		return query.from(company).list(company);
//	}
//
//	public Company initCompany(long id) {
//		Company company;
//		if (id == 0) {
//			company = new Company(null);
//		} else {
//			company = this.findById(id);
//		}
//		return company;
//	}
//}
