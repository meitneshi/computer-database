package com.excilys.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerMapper;
import com.excilys.om.Computer;
import com.excilys.service.ICompanyService;
import com.excilys.service.IComputerService;

/**
 * Controller implementation class AddComputerController
 */
@Controller
@RequestMapping("/AddComputer")
public class AddComputerController {
	
	@Autowired
	private ICompanyService companyservice;
	@Autowired
	private IComputerService computerservice;
	@Autowired
	private ComputerMapper compMapper;
	
    public AddComputerController() {
        super();
    }

    @RequestMapping(method=RequestMethod.GET)
	protected String doGet(Model model) {
		model.addAttribute("companyList", companyservice.findAll());
		model.addAttribute("add", false);
		model.addAttribute("computerdto", new ComputerDTO());
		return "addComputer";
	}

    @RequestMapping(method=RequestMethod.POST)
	protected String doPost(@ModelAttribute("computerdto") @Valid ComputerDTO computerdto, BindingResult result, Model model) {
		
    	if (result.hasErrors()) { //invalid information
    		model.addAttribute("displayDivAddError", true);
    		return "addComputer";
    	} else { //valid information
    		Computer computer = compMapper.toComputer(computerdto);
    		computerservice.save(computer);
    		model.addAttribute("add", true);
    		return "redirect:/Dashboard";
    	}
	}
}
