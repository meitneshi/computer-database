package com.excilys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerMapper;
import com.excilys.om.Computer;
import com.excilys.service.impl.CompanyServiceImpl;
import com.excilys.service.impl.ComputerServiceImpl;
import com.excilys.validator.ComputerValidator;


/**
 * Controller implementation class AddComputerController
 */
@Controller
@RequestMapping("/AddComputer")
public class AddComputerController {
	
	@Autowired
	private CompanyServiceImpl companyservice;
	@Autowired
	private ComputerServiceImpl computerservice;
	@Autowired
	private ComputerValidator compValidator;
	@Autowired
	private ComputerMapper compMapper;
	
    public AddComputerController() {
        super();
    }

    @RequestMapping(method=RequestMethod.GET)
	protected String doGet(HttpServletRequest request, HttpServletResponse response, Model model) {
		response.setContentType("text/html");
		model.addAttribute("companyList", companyservice.findAll());
		model.addAttribute("displayDivAdd", false);
		model.addAttribute("computerdto", new ComputerDTO());
		return "addComputer";
	}

    @RequestMapping(method=RequestMethod.POST)
	protected String doPost(@ModelAttribute("computerdto") ComputerDTO computerdto, HttpServletRequest request, HttpServletResponse response, Model model) {
		response.setContentType("text/html");
		System.out.println(computerdto);
		
		if (compValidator.validate(computerdto) == 0) { //good computer
			//convert the dto to a computer to add
			Computer computer = compMapper.toComputer(computerdto);
			System.out.println(computer);
			//add the computer
			computerservice.save(computer);
			//go to next page
			
			model.addAttribute("displayDivAdd", true);
			return "addComputer";
		} else {
			request.setAttribute("displayDivAddError", true);
			
			//Error gestion
			String errorCode = Integer.toBinaryString(compValidator.validate(computerdto));
			boolean errorName = ("1".equals(errorCode) | "11".equals(errorCode) | "101".equals(errorCode) | "111".equals(errorCode));
			boolean errorIntroduced = ("10".equals(errorCode) | "11".equals(errorCode) | "110".equals(errorCode) | "111".equals(errorCode));;
			boolean errorDiscontinued = ("100".equals(errorCode) | "110".equals(errorCode) | "101".equals(errorCode) | "111".equals(errorCode));;
			
			model.addAttribute("errorName", errorName);
			model.addAttribute("errorIntroduced", errorIntroduced);
			model.addAttribute("errorDiscontinued", errorDiscontinued);
			return "addComputer";
		}
	}
}
