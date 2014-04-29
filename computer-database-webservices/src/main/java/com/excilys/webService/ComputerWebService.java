package com.excilys.webService;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.om.Computer;
import com.excilys.service.IComputerService;

@WebService
public class ComputerWebService {

	@Autowired
	private IComputerService services;

	@WebMethod
	public List<Computer> findAll() {
		return services.findAll();
	}
	
}
