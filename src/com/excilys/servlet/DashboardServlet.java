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
		int numberOfComputer = compuDAO.count();
		
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
		double numberOfComputerDouble = (double) numberOfComputer;
		
		double pageMaxDouble = numberOfComputerDouble/entitiesPerPageDouble;
		int pageMax = (int) Math.ceil(pageMaxDouble);
				
		request.setAttribute("computerPageList", cppdao.findAllInPage(currentPageNumber, entitiesPerPage));
		request.setAttribute("numberOfComputer", numberOfComputer);
		request.setAttribute("pageMax", pageMax);
		request.setAttribute("offsetSQL", offsetSQL);
		request.setAttribute("currentPageNumber", currentPageNumber);
		if (entitiesPerPage == 0) {
			request.setAttribute("entitiesPerPage", numberOfComputer);
		} else {
			request.setAttribute("entitiesPerPage", entitiesPerPage);
		}
		
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		int epp = 0;
		//if search
		if (request.getParameter("filter") != null) {
			epp = Integer.parseInt(request.getParameter("epp"));
			String filter = request.getParameter("filter");
			response.sendRedirect("/computer_database/Dashboard?epp="+request.getParameter("epp")+"&search="+filter);
		}else{//default Dashboard
			epp = Integer.parseInt(request.getParameter("epp"));
			request.setAttribute("epp", epp);
			response.sendRedirect("/computer_database/Dashboard?epp="+request.getParameter("epp"));
		}
		
		
	}

}
