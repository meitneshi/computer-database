package com.excilys.mapper;

import com.excilys.dto.ComputerDTO;
import com.excilys.om.Computer;

public interface IComputerMapper {

	/**
	 * convert a computerDTO into a Computer
	 * @param computerDto the dto
	 * @return computer
	 */
	public Computer toComputer(ComputerDTO computerDto);
	
	/**
	 * convert a computer to a computerDto
	 * @param computer the computer
	 * @return computerDTO
	 */
	public ComputerDTO toDto(Computer computer);
}
