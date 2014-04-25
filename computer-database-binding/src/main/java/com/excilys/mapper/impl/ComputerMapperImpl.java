package com.excilys.mapper.impl;

import org.joda.time.DateTime;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;

import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.IComputerMapper;
import com.excilys.om.Company;
import com.excilys.om.Computer;
import com.excilys.service.ICompanyService;
import com.excilys.util.UtilDate;

@Component
public class ComputerMapperImpl implements IComputerMapper{

	private final static Logger logger = (Logger) LoggerFactory.getLogger(ComputerMapperImpl.class);
	
	@Autowired
	private ICompanyService companyservice;
	@Autowired
	private UtilDate utilDate;
	
	@Override
	public Computer toComputer(ComputerDTO computerDto) {
		DateTime introduced = utilDate.toDate(computerDto.getIntroduced());
		DateTime discontinued = utilDate.toDate(computerDto.getDiscontinued());
		Company company = null;
		int id = 0;
		try {
			id = Integer.parseInt(computerDto.getId());
		} catch (NumberFormatException e){
			logger.info("failed to parse the id into integer "+e.getMessage());
		}
		if ("0".equals(computerDto.getCompanyId())) {
			company = null;
		} else {
			long compId = Long.parseLong(computerDto.getCompanyId());
			company = companyservice.initCompany(compId);
		}
		Computer computer = new Computer(id, company, computerDto.getName(), introduced, discontinued);
		return computer;
	}
	
	@Override
	public ComputerDTO toDto(Computer computer) {
		String introduced = utilDate.toString(computer.getIntroduced());
		String discontinued = utilDate.toString(computer.getDiscontinued());
		String id = String.valueOf(computer.getId());
		String companyId = "";
		if (computer.getCompany() != null) {
			companyId = String.valueOf(computer.getCompany().getId());
		}
		ComputerDTO computerDTO = new ComputerDTO(id, computer.getName(), introduced, discontinued, companyId);
		return computerDTO;
	}
}
