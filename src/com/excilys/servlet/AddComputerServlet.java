package com.excilys.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.om.Company;
import com.excilys.om.Computer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;


/**
 * Servlet implementation class AddComputerServlet
 */
@WebServlet("/AddComputer")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		CompanyService companyservice = new CompanyService();
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
		ComputerService computerService = new ComputerService();
		CompanyService companyService = new CompanyService();
		String name = "";
		Computer computerToAdd = null;
		String discontinuedStr = request.getParameter("discontinuedDate");
		String introducedStr = request.getParameter("introducedDate");
		Date introduced = null;
		Date discontinued = null;
		
		name = request.getParameter("computerName");
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		if("".equals(introducedStr)) {
			System.out.println("intro=chainevide");
			try {
				discontinued = formatter.parse(discontinuedStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if("".equals(discontinuedStr)) {
			System.out.println("disco=chainevide");
			try {
				introduced = formatter.parse(introducedStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if (Integer.parseInt(request.getParameter("company")) == 0) {
			Company company = new Company(null);
			computerToAdd = new Computer(company, name, introduced, discontinued);
		} else {
			Company company = companyService.findById(Integer.parseInt(request.getParameter("company")));
			computerToAdd = new Computer(company, name, introduced, discontinued);
		}
		
		computerService.create(computerToAdd);
		
		request.setAttribute("displayDivAdd", true);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp");
		dispatcher.forward(request,response);
		
	}
}
