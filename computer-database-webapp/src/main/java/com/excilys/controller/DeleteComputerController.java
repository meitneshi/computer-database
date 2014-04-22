package com.excilys.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.classic.Logger;

import com.excilys.service.IComputerService;


/**
 * *Controller implementation class DeleteComputerController
 */
@Controller
@RequestMapping("/DeleteComputer")
public class DeleteComputerController {
	private final static Logger logger = (Logger) LoggerFactory.getLogger(DeleteComputerController.class);
	
	@Autowired
	private IComputerService computerService;

    public DeleteComputerController() {
        super();
    }

    @RequestMapping(method=RequestMethod.GET)
	protected String doGet(
			@RequestParam(value="id", required = true) int id,
			Model model) {
		try {
			computerService.delete(id);
		} catch (NumberFormatException e) {
			logger.debug("failed to parse id to delete into int "+e.getMessage());
		}
		model.addAttribute("delete", true);
		return "redirect:/Dashboard";
	}

    @RequestMapping(method=RequestMethod.POST)
    protected void doPost(Model model) {
    	//nothing to do
    }
}
