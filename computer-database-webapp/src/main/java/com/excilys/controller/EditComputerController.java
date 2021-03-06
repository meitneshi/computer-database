package com.excilys.controller;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.classic.Logger;

import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.IComputerMapper;
import com.excilys.om.Computer;
import com.excilys.service.ICompanyService;
import com.excilys.service.IComputerService;

/**
 * Controller implementation class EditComputerController
 */
@Controller
@RequestMapping("/EditComputer")
public class EditComputerController {
	private final static Logger logger = (Logger) LoggerFactory.getLogger(EditComputerController.class);
	
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private IComputerService computerService;
	@Autowired
	private IComputerMapper compMapper;
	
    public EditComputerController() {
        super();
    }
    
    @RequestMapping(method=RequestMethod.GET)
    protected String doGet(
    		@RequestParam(value="id", required = true) int id,
    		@RequestParam(value="error", required = false) String error,
    		Model model) {

		Computer finalComputer = null;
		ComputerDTO computerdto = null;
		
		try {
			finalComputer = computerService.getById(id);
			computerdto = compMapper.toDto(finalComputer);
		} catch (NumberFormatException e){
			logger.debug("failed to parse id into int "+e.getMessage());
		}
		
		model.addAttribute("computerdto", computerdto);
		model.addAttribute("displayDivEdit", false);	
		model.addAttribute("companyList", companyService.findAll());
		
		if("true".equals(error)) {
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
