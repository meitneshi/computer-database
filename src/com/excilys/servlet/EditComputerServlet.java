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

import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.om.Company;
import com.excilys.om.Computer;

/**
 * Servlet implementation class EditComputerServlet
 */
@WebServlet("/EditComputer")
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditComputerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		request.setAttribute("displayDivEdit", false);
		
		ComputerService computerService = new ComputerService();
		CompanyService companyService = new CompanyService();
		
		Computer finalComputer = computerService.findById(Integer.parseInt(request.getParameter("id")));
		
		request.setAttribute("computer", finalComputer);
		request.setAttribute("companyList", companyService.findAll());
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/editComputer.jsp");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		ComputerService computerService = new ComputerService();
		String name = "";
		int id = Integer.parseInt(request.getParameter("computerId"));
		String discontinuedStr = request.getParameter("discontinuedDate");
		String introducedStr = request.getParameter("introducedDate");
		System.out.println(discontinuedStr);
		Date discontinued = null;
		Date introduced = null;
		
		name = request.getParameter("computerName");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if("".equals(introducedStr)) {
			try {
				discontinued = formatter.parse(discontinuedStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if("".equals(discontinuedStr)) {
			try {
				introduced = formatter.parse(introducedStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		Company company = this.initCompany(request.getParameter("company"));
		
		Computer computer = new Computer(id, company, name, introduced, discontinued);
		
		computerService.save(computer);
		
		request.setAttribute("displayDivAdd", true);
		response.sendRedirect("/computer_database/Dashboard");	
	}
	
	private Company initCompany(String companyId) {
		Company company;
		int companyIdInt = 0;
		CompanyService companyService = new CompanyService();
		try {
			companyIdInt = Integer.parseInt(companyId);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		if (companyIdInt == 0) {
			company = new Company(null);
		} else {
			company = companyService.findById(companyIdInt);
		}
		return company;
	}
}
