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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.dao.DAOFactory;
import com.excilys.om.Computer;
import com.excilys.service.ComputerService;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/Dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = (Logger) LoggerFactory.getLogger(DAOFactory.class);
	private ComputerService computerService = ComputerService.INSTANCE;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		int entitiesPerPage = 0;
		int currentPageNumber = 0;
		int numberOfComputer = 0;
		int numberTotalOfComputer = 0;
		
//		traitement entitiesPerPage
		entitiesPerPage = this.initEntitiesPerPage(request.getParameter("entitiesperpage"));
		
//		traitement page
		currentPageNumber = this.initCurrentPageNumber(request.getParameter("page"));
		
//		set Criteria
		String criteria = null;
		if (request.getParameter("criteria") != null) {
			request.setAttribute("criteria", request.getParameter("criteria"));
			criteria = request.getParameter("criteria");
		} else {
			request.setAttribute("criteria", "name");
			criteria = "name";
		}
		
//		set Order
		String order = null;
		if(request.getParameter("order") != null) {
			request.setAttribute("order", request.getParameter("order"));
			order = request.getParameter("order");
		} else {
			request.setAttribute("order", "asc");
			order = "asc";
		}
		
//		traitement filtre
		if ((request.getParameter("filter")) != null) { //filter exists
			numberOfComputer = computerService.count(request.getParameter("filter"));
			request.setAttribute("filter", request.getParameter("filter"));
			String filter = request.getParameter("filter");
			List<Computer> result = this.findComputer(currentPageNumber, entitiesPerPage, filter, criteria, order);
			request.setAttribute("computerPageList", result);
		} else { //filter null, default
			numberOfComputer = computerService.count("");
			request.setAttribute("filter", "");
			List<Computer> result = this.findComputer(currentPageNumber, entitiesPerPage, "", criteria, order);
			request.setAttribute("computerPageList", result);
		}
		
//		Evaluation de Page max
		int pageMax = this.initPageMax(entitiesPerPage, numberOfComputer);
				
		numberTotalOfComputer = computerService.count("");
		request.setAttribute("nbTotal", numberTotalOfComputer);
		request.setAttribute("numberOfComputer", numberOfComputer);
		request.setAttribute("pageMax", pageMax);
		request.setAttribute("currentPageNumber", currentPageNumber);
		request.setAttribute("entitiesperpage", entitiesPerPage);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//nothing to do for the moment
	}
	
	
	/******************************************************/
	/*                     Functions                      */
	/******************************************************/

//	Traitement de currentPage
	private int initCurrentPageNumber(String currentPageNumberS) {
		int currentPageNumber = 1; //default
		if(currentPageNumberS != null) {
			try {
				currentPageNumber = Integer.parseInt(currentPageNumberS);
			} catch (NumberFormatException e) {
				logger.debug("failed to parse current Page number into int "+e.getMessage());
			}
		}
		return currentPageNumber;
	}
	
//	Initialisation de page Max
	private int initPageMax(int entitiesPerPage, int numberOfComputer) {
		double entitiesPerPageDouble = (double) entitiesPerPage;
		double numberOfComputerDouble = (double) numberOfComputer;
		double pageMaxDouble = numberOfComputerDouble/entitiesPerPageDouble;
		return (int) Math.ceil(pageMaxDouble);
	}

//	initialisation de entities par page
	private int initEntitiesPerPage(String entitiesPerPageS){
		int entitiesperpage = 30; //default
		if(entitiesPerPageS != null) {
			try {
				entitiesperpage =  Integer.parseInt(entitiesPerPageS);
			} catch (NumberFormatException e) {
				logger.debug("failed to parse number of entities per Page into int "+e.getMessage());
			}
		}
		return entitiesperpage;
	}

//	generate list of computer to be show on screen with order
	private List<Computer> findComputer(int currentPageNumber, int entitiesPerPage, String filter, String criteria, String order) {
		List<Computer> result = new ArrayList<Computer>();
		String filterS = "";
		if (filter != null) { //filter specified
			filterS = filter;
		}
		if ("desc".equals(order)) {
			result = computerService.findInPage(currentPageNumber, entitiesPerPage, filterS, order, criteria);
		}
		if ("asc".equals(order)) {
			result = computerService.findInPage(currentPageNumber, entitiesPerPage, filterS, order, criteria);
		}
		return result;
	}


}
