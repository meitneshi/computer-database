package com.excilys.webService;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.om.Computer;
import com.excilys.service.IComputerService;

@WebService
@Component
@Path("/webServices")
public class ComputerWebService {

	@Autowired
	private IComputerService services;

	@GET
	@Produces("application/xml")
	public List<Computer> findAll() {
		return services.findAll();
	}
	
}
