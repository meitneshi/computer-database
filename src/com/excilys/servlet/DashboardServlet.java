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
import com.excilys.om.Computer;

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
		
		int entitiesPerPage = 0;
		int currentPageNumber = 0;
		int offsetSQL = 0;
		int numberOfComputer = 0;
		int numberTotalOfComputer = 0;
		
		//traitement entitiesPerPage
		if(request.getParameter("entitiesperpage") != null) {
			entitiesPerPage = Integer.parseInt(request.getParameter("entitiesperpage"));
		} else {
			entitiesPerPage = 30; //default
		}
		
		//traitement page
		if(request.getParameter("page") != null) {
			currentPageNumber = Integer.parseInt(request.getParameter("page"));
			offsetSQL = (currentPageNumber-1)*entitiesPerPage;
		} else {
			currentPageNumber = 1; //default
			offsetSQL = 0; //default
		}
		
		if (request.getParameter("criteria") != null) {
			request.setAttribute("criteria", request.getParameter("criteria"));
		} else {
			request.setAttribute("criteria", "name");
		}
		if(request.getParameter("order") != null) {
			request.setAttribute("order", request.getParameter("order"));
		} else {
			request.setAttribute("order", "asc");
		}
		
		//traitement filtre et order
		List<Computer> result = new ArrayList<Computer>();
		if (request.getParameter("filter") != null) {
			request.setAttribute("filter", request.getParameter("filter"));
			numberOfComputer = compuDAO.count(request.getParameter("filter"));
			if(request.getParameter("order") != null && request.getParameter("criteria") != null) {//order specified
				
				if (request.getParameter("order").equals("des")){
					result = compuDAO.findInPage(currentPageNumber, entitiesPerPage, request.getParameter("filter").toString(), "DESC", request.getParameter("criteria").toString());
				}
				if (request.getParameter("order").equals("asc")) {
					result = compuDAO.findInPage(currentPageNumber, entitiesPerPage, request.getParameter("filter").toString(), "ASC", request.getParameter("criteria").toString());
				}
			}else { //default order (name ASC)
				result = compuDAO.findInPage(currentPageNumber, entitiesPerPage, request.getParameter("filter").toString(), "ASC", "name");
			}
			request.setAttribute("computerPageList", result);
			
		} else {
			numberOfComputer = compuDAO.count("");
			if(request.getParameter("order") != null && request.getParameter("criteria") != null) {//order specified
				if (request.getParameter("order").equals("des")){
					result = compuDAO.findInPage(currentPageNumber, entitiesPerPage, "", "DESC", request.getParameter("criteria").toString());
				}
				if (request.getParameter("order").equals("asc")) {
					result = compuDAO.findInPage(currentPageNumber, entitiesPerPage, "", "ASC", request.getParameter("criteria").toString());
				}
				request.setAttribute("order", request.getParameter("order"));
				request.setAttribute("criteria", request.getParameter("criteria"));
				
			}else { //default order (name ASC)
				result = compuDAO.findInPage(currentPageNumber, entitiesPerPage, "", "ASC", "name");
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
		int entitiesperpage = 0;
		int page = 0;
		StringBuilder urlB = new StringBuilder();
		urlB.append("/computer_database/Dashboard");
		//if order
		//if search
		if (request.getParameter("filter") != null) {
			entitiesperpage = Integer.parseInt(request.getParameter("entitiesperpage"));
			page = Integer.parseInt(request.getParameter("page"));
			urlB.append("?entitiesperpage=").append(entitiesperpage).append("&page=").append(page);
			if (request.getParameter("criteria") != null) {
				urlB.append("&criteria=").append(request.getParameter("criteria"));
			}
			if(request.getParameter("order") != null) {
				urlB.append("&order=").append(request.getParameter("order"));
			}
			String filter = request.getParameter("filter");
			urlB.append("&filter=").append(filter);
			
			String url = urlB.toString();
			response.sendRedirect(url);
		}else{//default Dashboard
			entitiesperpage = Integer.parseInt(request.getParameter("entitiesperpage"));
			page = Integer.parseInt(request.getParameter("page"));
			urlB.append("?entitiesperpage=").append(entitiesperpage).append("&page=").append(page);
			if (request.getParameter("criteria") != null) {
				urlB.append("&criteria=").append(request.getParameter("criteria"));
			}
			if(request.getParameter("order") != null) {
				urlB.append("&order=").append(request.getParameter("order"));
			}
			String filter = request.getParameter("filter");
			urlB.append("&filter=").append(filter);
			
			String url = urlB.toString();
			response.sendRedirect(url);
		}
		
		
	}

}
