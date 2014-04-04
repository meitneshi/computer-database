package com.excilys.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.qos.logback.classic.Logger;

import com.excilys.service.impl.CompanyServiceImpl;
import com.excilys.service.impl.ComputerServiceImpl;
import com.excilys.validator.ComputerValidator;
import com.excilys.dao.impl.ConnectionFactory;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerMapper;
import com.excilys.om.Computer;

/**
 * Controller implementation class EditComputerController
 */
@Controller
@RequestMapping("/EditComputer")
public class EditComputerController {
	private final static Logger logger = (Logger) LoggerFactory.getLogger(ConnectionFactory.class);
	
	@Autowired
	private CompanyServiceImpl companyService;
	@Autowired
	private ComputerServiceImpl computerService;
	@Autowired
	private ComputerValidator compValidator;
	@Autowired
	private ComputerMapper compMapper;
	
    public EditComputerController() {
        super();
    }
    
    @RequestMapping(method=RequestMethod.GET)
    protected String doGet(HttpServletRequest request, HttpServletResponse response, Model model) {
		response.setContentType("text/html");

		int id;
		Computer finalComputer = null;
		ComputerDTO computerdto = null;
		
		try {
			id = Integer.parseInt(request.getParameter("id"));
			finalComputer = computerService.findById(id);
			computerdto = compMapper.toDto(finalComputer);
		} catch (NumberFormatException e){
			logger.debug("failed to parse id into int "+e.getMessage());
		}
		
		model.addAttribute("computerdto", computerdto);
		model.addAttribute("displayDivEdit", false);	
		model.addAttribute("companyList", companyService.findAll());
		
		if("true".equals(request.getParameter("error"))) {
			model.addAttribute("displayDivEditError", true);
		}
		return "editComputer";
	}

    @RequestMapping(method=RequestMethod.POST)
	protected void doPost(@ModelAttribute("computerdto") ComputerDTO computerdto, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		String id = request.getParameter("computerId");
		
		if(compValidator.validate(computerdto) == 0) { //valid information
			//convert the DTO to a computer to edit
			Computer computer = compMapper.toComputer(computerdto);
			//edit the computer
			computerService.save(computer);
			//go to next page
			response.sendRedirect("Dashboard?edit=true");
		} else { //invalid information
			response.sendRedirect("EditComputer?id="+id+"&error=true");
		}
	}
}
