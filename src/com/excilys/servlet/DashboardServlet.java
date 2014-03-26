package com.excilys.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.dao.ComputerDAO;
import com.excilys.dao.ComputerPaginationDAO;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/Dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		ComputerDAO compuDAO = new ComputerDAO();
		ComputerPaginationDAO cppdao = new ComputerPaginationDAO();
		
		int entitiesPerPage = 0;
		int currentPageNumber = 0;
		int offsetSQL = 0;
		
		//traitement entitiesPerPage
		if(request.getParameter("epp") != null) {
			entitiesPerPage = Integer.parseInt(request.getParameter("epp"));
		} else {
			entitiesPerPage = 30; //default
		}
		
		if(request.getParameter("p") != null) {
			currentPageNumber = Integer.parseInt(request.getParameter("p"));
			offsetSQL = (currentPageNumber-1)*entitiesPerPage;
		} else {
			currentPageNumber = 1; //default
			offsetSQL = 0; //default
		}
		
		double entitiesPerPageDouble = (double) entitiesPerPage;
		double numberOfComputerDouble = (double) compuDAO.count();
		
		double pageMaxDouble = numberOfComputerDouble/entitiesPerPageDouble;
		int pageMax = (int) Math.ceil(pageMaxDouble);
				
		request.setAttribute("computerPageList", cppdao.findAllInPage(currentPageNumber, entitiesPerPage));
		request.setAttribute("numberOfComputer", compuDAO.count());
		request.setAttribute("pageMax", pageMax);
		request.setAttribute("offsetSQL", offsetSQL);
		request.setAttribute("currentPageNumber", currentPageNumber);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
