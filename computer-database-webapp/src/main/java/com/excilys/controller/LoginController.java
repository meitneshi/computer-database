package com.excilys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/Login")
public class LoginController {
	
    public LoginController() {
        super();
    }
    
    @RequestMapping(method=RequestMethod.GET)
	protected String doGet(Model model,
			@RequestParam(value="error", required = false) String error , 
			@RequestParam(value="disconnect", required = false) String disco) {
    	
    	model.addAttribute("error", false);
    	model.addAttribute("disconnect", false);
    	
    	if (error != null) {
    		model.addAttribute("error", true);
    	}
    	if (disco != null) {
    		model.addAttribute("disconnect", true);
    	}
    	return "login";
	}
}
