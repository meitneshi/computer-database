package com.excilys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/Langage")
public class LangageController {
	
	@RequestMapping(method=RequestMethod.GET)
	protected ModelAndView doGet(@RequestHeader(value="referer") String previousPage) {
		return new ModelAndView("redirect:"+previousPage);
	}

}
