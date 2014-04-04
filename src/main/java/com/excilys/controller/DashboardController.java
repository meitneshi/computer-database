package com.excilys.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	protected String doGet(HttpServletRequest request, HttpServletResponse response, Model model) {
		response.setContentType("text/html");
		
		PageWrapper page = new PageWrapper();
		page = computerService.generatePage(
				request.getParameter("page"), 
				request.getParameter("entitiesperpage"), 
				request.getParameter("filter"), 
				request.getParameter("order"), 
				request.getParameter("page"));
		
		model.addAttribute("page", page);
		model.addAttribute("edit", request.getParameter("edit"));
		
		return "dashboard";
		
		
	}
    @RequestMapping(method=RequestMethod.POST)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//nothing to do for the moment
	}
}