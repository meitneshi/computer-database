package com.excilys.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.dao.CompanyDAO;
import com.excilys.dao.ComputerDAO;
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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		request.setAttribute("displayDivEdit", false);
		
		ComputerDAO computerDAO = new ComputerDAO();
		CompanyDAO companyDAO = new CompanyDAO();
		
		Computer finalComputer = computerDAO.findById(Integer.parseInt(request.getParameter("id")));
		
		request.setAttribute("computer", finalComputer);
		request.setAttribute("companyList", companyDAO.findAll());
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/editComputer.jsp");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		ComputerDAO computerDAO = new ComputerDAO();
		CompanyDAO companyDAO = new CompanyDAO();
		String name = request.getParameter("computerName");
		String discontinuedStr = request.getParameter("introducedDate");
		String introducedStr = request.getParameter("discontinuedDate");
		Timestamp introduced = null;
		Timestamp discontinued = null;
		int id = Integer.parseInt(request.getParameter("computerId"));
		
		if (!request.getParameter("introducedDate").equals("")) {
			introducedStr = request.getParameter("introducedDate") + " 00:00:00";
			introduced = java.sql.Timestamp.valueOf(introducedStr);
		}
		
		if (!request.getParameter("discontinuedDate").equals("")) {
			discontinuedStr = request.getParameter("discontinuedDate") + " 00:00:00";
			discontinued = java.sql.Timestamp.valueOf(discontinuedStr);
		}
		
		Company company;
		if (Integer.parseInt(request.getParameter("company")) == 0) {
			company = new Company(null);
		} else {
			company = companyDAO.findById(Integer.parseInt(request.getParameter("company")));
		}
		Computer computerToModify = new Computer(id, company, name, introduced, discontinued);
		
		computerDAO.update(computerToModify);
		
		request.setAttribute("displayDivEdit", true);
		
		response.sendRedirect("/computer_database/Dashboard");		
	}
	
	

}
