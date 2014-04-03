package com.excilys.mapper;

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

@Component
public class ComputerMapper {

	private final static Logger logger = (Logger) LoggerFactory.getLogger(ConnectionFactory.class);
	
	@Autowired
	private CompanyServiceImpl companyservice;
	
	/**
	 * convert a computerDTO into a Computer
	 * @param computerDto
	 * @return
	 */
	public Computer toComputer(ComputerDTO computerDto) {
		Date introduced = UtilDate.toDate(computerDto.getIntroduced());
		Date discontinued = UtilDate.toDate(computerDto.getDiscontinued());
		int id = 0;
		if (companyservice == null) {
			System.out.println("coucou");
		}
		try {
			id = Integer.parseInt(computerDto.getId());
		} catch (NumberFormatException e){
			logger.info("failed to parse the id into integer "+e.getMessage());
		}
		Company company = companyservice.initCompany(computerDto.getCompanyId());
		Computer computer = new Computer(id, company, computerDto.getName(), introduced, discontinued);
		return computer;
	}
	
	/**
	 * convert a computer to a computerDto
	 * @param computer
	 * @return
	 */
	public ComputerDTO toDto(Computer computer) {
		String introduced = UtilDate.toString(computer.getIntroduced());
		String discontinued = UtilDate.toString(computer.getDiscontinued());
		String id = String.valueOf(computer.getId());
		String companyId = String.valueOf(computer.getCompany().getId());
		
		ComputerDTO computerDTO = new ComputerDTO(id, computer.getName(), introduced, discontinued, companyId);
		return computerDTO;
	}
}
