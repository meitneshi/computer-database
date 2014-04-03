package com.excilys.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


import com.excilys.dto.ComputerDTO;
import com.excilys.om.Computer;
import com.excilys.service.impl.CompanyServiceImpl;
import com.excilys.service.impl.ComputerServiceImpl;
import com.excilys.validator.ComputerValidator;


/**
 * Servlet implementation class AddComputerServlet
 */
@WebServlet("/AddComputer")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	@Autowired
	private CompanyServiceImpl companyservice;
	
	@Autowired
	private ComputerServiceImpl computerservice;
	
	@Autowired
	private ComputerValidator compValidator;
    
	/**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException{
    	super.init();
    	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		request.setAttribute("companyList", companyservice.findAll());
		request.setAttribute("displayDivAdd", false);
				
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String name = request.getParameter("computerName");
		String discontinuedStr = request.getParameter("discontinuedDate");
		String introducedStr = request.getParameter("introducedDate");
		String companyId = request.getParameter("company");
		
		ComputerDTO compdto = new ComputerDTO("0", name, introducedStr, discontinuedStr, companyId);
		
		if (compValidator.validate(compdto)) { //good computer
			//convert the dto to a computer to add
			Computer computer = compValidator.toComputer(compdto);
			//add the computer
			computerservice.save(computer);
			//go to next page
			request.setAttribute("displayDivAdd", true);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp");
			dispatcher.forward(request,response);
		} else {
			request.setAttribute("displayDivAddError", true);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp");
			dispatcher.forward(request,response);
		}
		
		
		
		
		
		
		
		
		
		
	}
}
