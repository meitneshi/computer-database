package com.excilys.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.service.impl.ComputerServiceImpl;
import com.excilys.wrapper.PageWrapper;

/**
 * Controller implementation class DashboardController
 */
@Controller
@RequestMapping("/Dashboard")
public class DashboardController {
	
	@Autowired
	private ComputerServiceImpl computerService;
       
    public DashboardController() {
        super();
    }
    
    @RequestMapping(method=RequestMethod.GET)
	protected String doGet(
			@RequestParam(value="page", required = false) String page,
			@RequestParam(value="entitiesperpage", required = false) String entitiesPerPage,
			@RequestParam(value="filter", required = false) String filter,
			@RequestParam(value="order", required = false) String order,
			@RequestParam(value="criteria", required = false) String criteria,
			Model model) {
		
		PageWrapper pageW = new PageWrapper();
		pageW = computerService.generatePage(page, entitiesPerPage, filter, order, criteria);
		
		model.addAttribute("page", pageW);
//		model.addAttribute("edit", request.getParameter("edit"));
		
		return "dashboard";
		
		
	}
    @RequestMapping(method=RequestMethod.POST)
	protected void doPost() {
		//nothing to do for the moment
	}
}