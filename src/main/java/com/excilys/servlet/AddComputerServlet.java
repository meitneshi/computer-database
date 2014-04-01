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

import ch.qos.logback.classic.Logger;

import com.excilys.dao.impl.DAOFactory;
import com.excilys.om.Company;
import com.excilys.om.Computer;
import com.excilys.service.impl.CompanyServiceImpl;
import com.excilys.service.impl.ComputerServiceImpl;


/**
 * Servlet implementation class AddComputerServlet
 */
@WebServlet("/AddComputer")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	private final static Logger logger = (Logger) LoggerFactory.getLogger(DAOFactory.class);
	private CompanyServiceImpl companyservice = CompanyServiceImpl.INSTANCE;
	private ComputerServiceImpl computerservice = ComputerServiceImpl.INSTANCE;
	
	
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
		
		request.setAttribute("companyList", companyservice.findAll());
		request.setAttribute("displayDivAdd", false);
				
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String name = "";
		String discontinuedStr = request.getParameter("discontinuedDate");
		String introducedStr = request.getParameter("introducedDate");
		
		Date introduced = null;
		Date discontinued = null;
		
		name = request.getParameter("computerName");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if("".equals(introducedStr)) {
			try {
				discontinued = formatter.parse(discontinuedStr);
			} catch (ParseException e) {
				logger.debug("failed to format the discontinued date" +e.getMessage());
			}
		} else if("".equals(discontinuedStr)) {
			try {
				introduced = formatter.parse(introducedStr);
			} catch (ParseException e) {
				logger.debug("failed to format the introduced date" +e.getMessage());
			}
		} else{
			try {
				discontinued = formatter.parse(discontinuedStr);
				introduced = formatter.parse(introducedStr);
			} catch (ParseException e) {
				logger.debug("failed to format the introduced date and the discontinued date" +e.getMessage());
			}
		}
		
		Company company = companyservice.initCompany(request.getParameter("company"));
		
		Computer computer = new Computer(0, company, name, introduced, discontinued);
		
		computerservice.save(computer);
		
		request.setAttribute("displayDivAdd", true);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp");
		dispatcher.forward(request,response);
	}
}
