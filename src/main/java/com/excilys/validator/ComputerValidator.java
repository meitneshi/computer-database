package com.excilys.validator;

import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;

import com.excilys.dao.impl.ConnectionFactory;
import com.excilys.dto.ComputerDTO;
import com.excilys.om.Company;
import com.excilys.om.Computer;
import com.excilys.service.impl.CompanyServiceImpl;
import com.excilys.util.UtilDate;

/**
 * this Class represent the back validator for validation of input or update in DB
 * the validator receive a DTO representing the entity to validate and act
 * @author mbibos
 *
 */
@Component
public class ComputerValidator {

	private final static Logger logger = (Logger) LoggerFactory.getLogger(ConnectionFactory.class);
	
	@Autowired
	private CompanyServiceImpl companyservice;
	
	/**
	 * Constructor
	 */
	public ComputerValidator() {
		super();
	}
	
	/*va dnas le mapper de computer*/
	/**
	 * convert a computerDTO into a Computer
	 * @param computerDto
	 * @return
	 */
	public Computer toComputer(ComputerDTO computerDto) {
		Date introduced = UtilDate.toDate(computerDto.getIntroduced());
		Date discontinued = UtilDate.toDate(computerDto.getDiscontinued());
		if (companyservice == null) {
			System.out.println("coucou");
		}
		Company company = companyservice.initCompany(computerDto.getCompanyId());
		Computer computer = new Computer(0, company, computerDto.getName(), introduced, discontinued);
		return computer;
	}
	
	/**
	 * convert a computer to a computerDto
	 * @param computer
	 * @return
	 */
	public ComputerDTO toDto(Computer computer) {
		
		return null;
	}
	/*--------------------------*/
	
	public boolean validate(ComputerDTO computerDTO) {
		//check the name -> not null and at least 2 character
		boolean ret = true;
		if (computerDTO.getName() == null && computerDTO.getName().length() < 2) {
			logger.debug("Invalid name");
			return false;
		}else {
			logger.info("name checked and good");
		}
		
		Date introduced = null;
		Date discontinued = null;
		
		if(!"".equals(computerDTO.getIntroduced())) {
			logger.info("attempting to convert introduced Date to type Date");
			introduced = UtilDate.toDate(computerDTO.getIntroduced());
			if (introduced == null) {
				return false;
			}
		}
		if(!"".equals(computerDTO.getDiscontinued())) {
			logger.info("attempting to convert discontinued Date to type Date");
			discontinued = UtilDate.toDate(computerDTO.getDiscontinued());
			if (discontinued == null) {
				return false;
			}
		}
		return ret;
	}
}

