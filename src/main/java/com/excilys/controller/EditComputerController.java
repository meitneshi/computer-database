package com.excilys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    protected String doGet(HttpServletRequest request, Model model) {

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
	protected String doPost(@ModelAttribute("computerdto") @Valid ComputerDTO computerdto, BindingResult result, Model model) {
		
		if (result.hasErrors()) {//invalid informations
			model.addAttribute("displayDivEditError", true);
			return "editComputer";
		} else { //valid information
			Computer computer = compMapper.toComputer(computerdto);
			computerService.save(computer);
			model.addAttribute("edit", true);
			return "redirect:/Dashboard";
		}
	}
}
