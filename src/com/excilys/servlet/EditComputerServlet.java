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
import com.excilys.domainClass.Company;
import com.excilys.domainClass.Computer;

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
		
		
//		PrintWriter out = response.getWriter();
//		out.println(finalComputer);
		
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
		Computer computerToModify = new Computer();
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
		
		if (Integer.parseInt(request.getParameter("company")) == 0) {
			Company company = new Company(null);
			computerToModify.setId(id);
			computerToModify.setCompany(company);
			computerToModify.setName(name);
			computerToModify.setIntroduced(introduced);
			computerToModify.setDiscontinued(discontinued);
		} else {
			Company company = new Company(Integer.parseInt(request.getParameter("company")));
			Company company2 = companyDAO.find(company).get(0);
			computerToModify.setId(id);
			computerToModify.setCompany(company2);
			computerToModify.setName(name);
			computerToModify.setIntroduced(introduced);
			computerToModify.setDiscontinued(discontinued);
		}
		
		computerDAO.update(computerToModify);
		
		request.setAttribute("displayDivEdit", true);
		
		response.sendRedirect("/computer_database/Dashboard");		
	}

}
