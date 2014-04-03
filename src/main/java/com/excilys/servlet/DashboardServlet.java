package com.excilys.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.service.impl.ComputerServiceImpl;
import com.excilys.wrapper.PageWrapper;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/Dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ComputerServiceImpl computerService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {
        super();
    }
    
    @Override
    public void init() throws ServletException{
    	super.init();
    	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PageWrapper page = new PageWrapper();
		page = computerService.generatePage(
				request.getParameter("page"), 
				request.getParameter("entitiesperpage"), 
				request.getParameter("filter"), 
				request.getParameter("order"), 
				request.getParameter("page"));
		
		request.setAttribute("page", page);
		request.setAttribute("edit", request.getParameter("edit"));
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//nothing to do for the moment
	}
}