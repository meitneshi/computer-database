package com.excilys.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ch.qos.logback.classic.Logger;

import com.excilys.dao.impl.ConnectionFactory;
import com.excilys.dto.ComputerDTO;
import com.excilys.om.Company;
import com.excilys.om.Computer;
import com.excilys.service.impl.CompanyServiceImpl;

/**
 * this Class represent the back validator for validation of input or update in DB
 * the validator receive a DTO representing the entity to validate and act
 * @author mbibos
 *
 */
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
	
	/**
	 * convert a computerDTO into a Computer
	 * @param computerDto
	 * @return
	 */
	public Computer toComputer(ComputerDTO computerDto) {
		Company company = companyservice.initCompany(computerDto.getCompanyId());
		Computer computer = new Computer(0, company, computerDto.getName(), introduced, discontinued)
		return computer;
	}
	
	/**
	 * convert a computer to a computerDto
	 * @param computer
	 * @return
	 */
	public ComputerDTO toDto(Computer computer) {
		
		
		return null;
		//todo
	}
	
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
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		if(!"".equals(computerDTO.getIntroduced())) {
			try {
				introduced = formatter.parse(computerDTO.getIntroduced());
			} catch (ParseException e) {
				logger.debug("failed to format the introduced date" +e.getMessage());
				return false;
			}
		}
		if(!"".equals(computerDTO.getDiscontinued())) {
			try {
				introduced = formatter.parse(computerDTO.getDiscontinued());
			} catch (ParseException e) {
				logger.debug("failed to format the discontinued date" +e.getMessage());
				return false;
			}
		}
		return ret;
	}
}

