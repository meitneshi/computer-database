package com.excilys.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.dao.CompanyDAO;
import com.excilys.dao.ComputerDAO;
import com.excilys.domainClass.Company;
import com.excilys.domainClass.Computer;


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
		
		CompanyDAO companyDao = new CompanyDAO();
		request.setAttribute("companyList", companyDao.findAll());
		request.setAttribute("displayDiv", false);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		ComputerDAO computerDAO = new ComputerDAO();
		CompanyDAO companyDAO = new CompanyDAO();
		String name = "";
		Computer computerToAdd = null;
		String discontinuedStr = "";
		String introducedStr = "";
		Timestamp introduced = null;
		Timestamp discontinued = null;
		
		name = request.getParameter("computerName");
		
		if (!request.getParameter("introducedDate").equals("")) {
			introducedStr = request.getParameter("introducedDate") + " 00:00:00";
			introduced = java.sql.Timestamp.valueOf(introducedStr);
		}
		
		if (!request.getParameter("discontinuedDate").equals("")) {
			discontinuedStr = request.getParameter("discontinuedDate") + " 00:00:00";
			discontinued = java.sql.Timestamp.valueOf(discontinuedStr);
		}
		
		if (Integer.parseInt(request.getParameter("company")) == 0) {
			Company company = new Company(null);
			computerToAdd = new Computer(company, name, introduced, discontinued);
		} else {
			Company company = new Company(Integer.parseInt(request.getParameter("company")));
			Company company2 = companyDAO.find(company).get(0);
			computerToAdd = new Computer(company2, name, introduced, discontinued);
		}
//		PrintWriter out = response.getWriter();
//		out.println(computerToAdd);
		
		computerDAO.create(computerToAdd);
		
		request.setAttribute("displayDiv", true);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp");
		dispatcher.forward(request,response);
		
	}
}
