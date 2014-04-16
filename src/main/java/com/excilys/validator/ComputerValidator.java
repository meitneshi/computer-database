package com.excilys.validator;

import org.joda.time.DateTime;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;

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

	private final static Logger logger = (Logger) LoggerFactory.getLogger(ComputerValidator.class);
	
	/*
	 * errorCode : an int representing the code of the error based on a byte increments (1,2,4,8,...)
	 * 1 -> error on name					=> error 3
	 * 2 -> error on introduced date		=> error 2
	 * 4 -> error on discontinued date		=> error 1
	 * 
	 * error can be cumulated
	 * example : an error on the two dates at the same time -> error code = 4+2 = 6 -> transform in binary -> 110
	 * -> parsing -> error in position 1 and 2 -> dates error
	 * */
	
	/**
	 * Constructor
	 */
	public ComputerValidator() {
		super();
	}
	
	public int validate(ComputerDTO computerDTO) {
		int errorCode = 0;
		//check the ids
		logger.info("checking computer id");
		if (!(computerDTO.getId().matches("\\d+"))) { //invalid number
			logger.debug("invalid id, not an integer or long");
		} else {
			logger.info("computer id checked");
		}
		
		logger.info("checking company id");
		if (!(computerDTO.getId().matches("\\d+"))) { //invalid number
			logger.debug("invalid id, not an integer or long");
		} else {
			logger.info("company id checked");
		}

		//check the name -> not null and at least 2 character
		if ("".equals(computerDTO.getName()) || computerDTO.getName().length() < 2) {
			logger.debug("Invalid name");
			errorCode += 1;
		}else {
			logger.info("name checked and good");
		}
		
		//check the dates
		DateTime introduced = null;
		DateTime discontinued = null;
		if(!"".equals(computerDTO.getIntroduced()) || computerDTO.getIntroduced().matches("^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$")) {
			logger.info("attempting to convert introduced Date to type Date");
			introduced = UtilDate.toDate(computerDTO.getIntroduced());
			if (introduced == null) {
				errorCode += 2;
			}
		}
		if(!"".equals(computerDTO.getDiscontinued()) || computerDTO.getDiscontinued().matches("^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$")) {
			logger.info("attempting to convert discontinued Date to type Date");
			discontinued = UtilDate.toDate(computerDTO.getDiscontinued());
			if (discontinued == null) {
				errorCode += 4;
			}
		}
		return errorCode;
	}
}

