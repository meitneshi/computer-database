package com.excilys.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		String discontinuedStr = request.getParameter("discontinuedDate");
		String introducedStr = request.getParameter("introducedDate");
		
		name = request.getParameter("computerName");
		List<Date> dates = this.initDate(introducedStr, discontinuedStr);
		
		Company company;
		if (Integer.parseInt(request.getParameter("company")) == 0) {
			company = new Company(null);
		} else {
			company = companyService.findById(Integer.parseInt(request.getParameter("company")));
		}
		Computer computerToAdd = new Computer(company, name, dates.get(0), dates.get(1));
		
		computerService.create(computerToAdd);
		
		request.setAttribute("displayDivAdd", true);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp");
		dispatcher.forward(request,response);
		
	}

	private List<Date> initDate(String introducedStr, String discontinuedStr) {
		List<Date> dates = new ArrayList<Date>();
		dates.add(0, null);//introduced
		dates.add(1, null);//discontinued
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if("".equals(introducedStr)) {
			try {
				Date discontinued = formatter.parse(discontinuedStr);
				dates.set(1, discontinued);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if("".equals(discontinuedStr)) {
			try {
				Date introduced = formatter.parse(introducedStr);
				dates.set(0, introduced);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return dates;
	}
}
