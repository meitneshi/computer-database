package com.excilys.validator;

import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;

import com.excilys.dao.impl.ConnectionFactory;
import com.excilys.dto.ComputerDTO;
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
	
	
	
	/**
	 * Constructor
	 */
	public ComputerValidator() {
		super();
	}
	
	/*va dnas le mapper de computer*/
	
	/*--------------------------*/
	
	public boolean validate(ComputerDTO computerDTO) {
		boolean ret = true;
		
		//check the id
				try {
					Integer.parseInt(computerDTO.getId());
					logger.info("id check and good");
				} catch (NumberFormatException e) {
					logger.debug("invalid id, not an integer or long");
					return false;
				}
		//check the name -> not null and at least 2 character
		
		if (computerDTO.getName() == null && computerDTO.getName().length() < 2) {
			logger.debug("Invalid name");
			return false;
		}else {
			logger.info("name checked and good");
		}
		
		//check the dates
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

