package com.excilys.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import ch.qos.logback.classic.Logger;

import com.excilys.service.impl.CompanyServiceImpl;
import com.excilys.service.impl.ComputerServiceImpl;
import com.excilys.dao.impl.ConnectionFactory;
import com.excilys.om.Company;
import com.excilys.om.Computer;

/**
 * Servlet implementation class EditComputerServlet
 */
@WebServlet("/EditComputer")
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = (Logger) LoggerFactory.getLogger(ConnectionFactory.class);
	
	@Autowired
	private CompanyServiceImpl companyService;
	
	@Autowired
	private ComputerServiceImpl computerService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditComputerServlet() {
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
		
		request.setAttribute("displayDivEdit", false);
		
		int id;
		Computer finalComputer = null;
		
		try {
			id = Integer.parseInt(request.getParameter("id"));
			finalComputer = computerService.findById(id);
		} catch (NumberFormatException e){
			logger.debug("failed to parse id into int "+e.getMessage());
		}
		
		request.setAttribute("computer", finalComputer);
		request.setAttribute("companyList", companyService.findAll());
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/editComputer.jsp");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String name = "";
		int id = Integer.parseInt(request.getParameter("computerId"));
		String discontinuedStr = request.getParameter("discontinuedDate");
		String introducedStr = request.getParameter("introducedDate");
		System.out.println(discontinuedStr);
		Date discontinued = null;
		Date introduced = null;
		
		name = request.getParameter("computerName");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if(!"".equals(introducedStr)) {
			try {
				introduced = formatter.parse(introducedStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(!"".equals(discontinuedStr)) {
			try {
				discontinued = formatter.parse(discontinuedStr);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		
//		if("".equals(introducedStr)) {
//			try {
//				discontinued = formatter.parse(discontinuedStr);
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//		}
//		if("".equals(discontinuedStr)) {
//			try {
//				introduced = formatter.parse(introducedStr);
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//		}
		
		Company company = companyService.initCompany(request.getParameter("company"));
		
		Computer computer = new Computer(id, company, name, introduced, discontinued);
		
		computerService.save(computer);
		
		request.setAttribute("displayDivAdd", true);
		response.sendRedirect("Dashboard");	
	}
}
