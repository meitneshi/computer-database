package com.excilys.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.qos.logback.classic.Logger;

import com.excilys.dao.impl.ConnectionFactory;
import com.excilys.service.impl.ComputerServiceImpl;


/**
 * *Controller implementation class DeleteComputerController
 */
@Controller
@RequestMapping("/DeleteComputer")
public class DeleteComputerController {
	private final static Logger logger = (Logger) LoggerFactory.getLogger(ConnectionFactory.class);
	
	@Autowired
	private ComputerServiceImpl computerService;

    public DeleteComputerController() {
        super();
    }

    @RequestMapping(method=RequestMethod.GET)
	protected String doGet(Model model, HttpServletRequest request) {
		try {
			int idToDelete = Integer.parseInt(request.getParameter("id"));
			computerService.delete(idToDelete);
		} catch (NumberFormatException e) {
			logger.debug("failed to parse id to delete into int "+e.getMessage());
		}
		model.addAttribute("delete", true);
		return "redirect:/Dashboard";
	}

    @RequestMapping(method=RequestMethod.POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//nothing to do
    }
}
