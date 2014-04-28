package com.excilys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/Login")
public class LoginController {
	
    public LoginController() {
        super();
    }
    
    @RequestMapping(method=RequestMethod.GET)
	protected String doGet(
			Model model) {
		return "login";
	}
    
    @RequestMapping(method=RequestMethod.POST)
	protected void doPost() {
		//nothing to do for the moment
	}
}
