package com.excilys.mapper;

import com.excilys.dto.ComputerDTO;
import com.excilys.om.Computer;

public interface IComputerMapper {

	/**
	 * convert a computerDTO into a Computer
	 * @param computerDto
	 * @return
	 */
	public Computer toComputer(ComputerDTO computerDto);
	
	/**
	 * convert a computer to a computerDto
	 * @param computer
	 * @return
	 */
	public ComputerDTO toDto(Computer computer);
}
