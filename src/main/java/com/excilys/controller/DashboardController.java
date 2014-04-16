package com.excilys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.service.IComputerService;
import com.excilys.wrapper.PageWrapper;

/**
 * Controller implementation class DashboardController
 */
@Controller
@RequestMapping("/Dashboard")
public class DashboardController {
	
	@Autowired
	private IComputerService computerService;
       
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
			@RequestParam(value="add", required = false) String add,
			@RequestParam(value="edit", required = false) String edit,
			@RequestParam(value="delete", required = false) String delete,
			Model model) {
		
    	//Generate Page
		PageWrapper pageW = new PageWrapper();
		pageW = computerService.generatePage(page, entitiesPerPage, filter, order, criteria, add, edit, delete);
		
		//Add lang parameter
		model.addAttribute("lang", LocaleContextHolder.getLocale());

		model.addAttribute("page", pageW);
		return "dashboard";
	}
    
    @RequestMapping(method=RequestMethod.POST)
	protected void doPost() {
		//nothing to do for the moment
	}
}