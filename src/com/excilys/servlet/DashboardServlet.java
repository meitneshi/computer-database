package com.excilys.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.dao.ComputerDAO;
import com.excilys.dao.ComputerPaginationDAO;
import com.excilys.domainClass.Computer;

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
		int numberOfComputer = 0;
		int numberTotalOfComputer = 0;
		String order = "";
		
		//traitement entitiesPerPage
		if(request.getParameter("epp") != null) {
			entitiesPerPage = Integer.parseInt(request.getParameter("epp"));
		} else {
			entitiesPerPage = 30; //default
		}
		
		//traitement page
		if(request.getParameter("p") != null) {
			currentPageNumber = Integer.parseInt(request.getParameter("p"));
			offsetSQL = (currentPageNumber-1)*entitiesPerPage;
		} else {
			currentPageNumber = 1; //default
			offsetSQL = 0; //default
		}
		
		//traitement filtre et order
		List<Computer> result = new ArrayList<Computer>();
		if (request.getParameter("filter") != null) {
			request.setAttribute("filter", request.getParameter("filter"));
			numberOfComputer = compuDAO.count(request.getParameter("filter"));
			if(request.getParameter("on") != null && request.getParameter("c") != null) {//order specified
				if (request.getParameter("on").equals("des")){
					result = cppdao.findSearchInPage(currentPageNumber, entitiesPerPage, request.getParameter("filter").toString(), "DESC", request.getParameter("c").toString());
				}
				if (request.getParameter("on").equals("asc")) {
					result = cppdao.findSearchInPage(currentPageNumber, entitiesPerPage, request.getParameter("filter").toString(), "ASC", request.getParameter("c").toString());
				}
				
			}else { //default order (name ASC)
				result = cppdao.findSearchInPage(currentPageNumber, entitiesPerPage, request.getParameter("filter").toString(), "ASC", "name");
			}
			request.setAttribute("computerPageList", result);
			
		} else {
			numberOfComputer = compuDAO.count("");
			if(request.getParameter("on") != null && request.getParameter("c") != null) {//order specified
				if (request.getParameter("on").equals("des")){
					result = cppdao.findAllInPage(currentPageNumber, entitiesPerPage, "DESC", request.getParameter("c").toString());
				}
				if (request.getParameter("on").equals("asc")) {
					result = cppdao.findAllInPage(currentPageNumber, entitiesPerPage, "ASC", request.getParameter("c").toString());
				}
				request.setAttribute("order", request.getParameter("on"));
				request.setAttribute("criteria", request.getParameter("c"));
				
			}else { //default order (name ASC)
				result = cppdao.findAllInPage(currentPageNumber, entitiesPerPage, "ASC", "name");
				request.setAttribute("order", "asc");
				request.setAttribute("criteria", "name");
			}
			request.setAttribute("computerPageList", result);
		}
		
		double entitiesPerPageDouble = (double) entitiesPerPage;
		double numberOfComputerDouble = (double) numberOfComputer;
		
		double pageMaxDouble = numberOfComputerDouble/entitiesPerPageDouble;
		int pageMax = (int) Math.ceil(pageMaxDouble);
				
		numberTotalOfComputer = compuDAO.count("");
		request.setAttribute("nbTotal", numberTotalOfComputer);
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
			request.setAttribute("epp", epp);
			
			String filter = request.getParameter("filter");
			request.setAttribute("filter", filter);
			
			this.doGet(request, response);
		}else{//default Dashboard
			epp = Integer.parseInt(request.getParameter("epp"));
			request.setAttribute("epp", epp);
			this.doGet(request, response);
		}
		
		
	}

}
