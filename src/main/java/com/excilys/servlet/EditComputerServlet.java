package com.excilys.servlet;

import java.io.IOException;

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
import com.excilys.validator.ComputerValidator;
import com.excilys.dao.impl.ConnectionFactory;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerMapper;
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
	@Autowired
	private ComputerValidator compValidator;
	@Autowired
	private ComputerMapper compMapper;
	
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

		int id;
		Computer finalComputer = null;
		
		try {
			id = Integer.parseInt(request.getParameter("id"));
			finalComputer = computerService.findById(id);
		} catch (NumberFormatException e){
			logger.debug("failed to parse id into int "+e.getMessage());
		}
		
		request.setAttribute("displayDivEdit", false);
		request.setAttribute("computer", finalComputer);
		request.setAttribute("companyList", companyService.findAll());
		
		if("true".equals(request.getParameter("error"))) {
			request.setAttribute("displayDivEditError", true);
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/editComputer.jsp");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String name = request.getParameter("computerName");
		String id = request.getParameter("computerId");
		String discontinuedStr = request.getParameter("discontinuedDate");
		String introducedStr = request.getParameter("introducedDate");
		String companyId = request.getParameter("company");
		
		ComputerDTO compdto = new ComputerDTO(id, name, introducedStr, discontinuedStr, companyId);
		
		if(compValidator.validate(compdto) == 0) { //valid information
			//convert the DTO to a computer to edit
			Computer computer = compMapper.toComputer(compdto);
			//edit the computer
			computerService.save(computer);
			//go to next page
			response.sendRedirect("Dashboard?edit=true");
		} else { //invalid information
			response.sendRedirect("EditComputer?id="+id+"&error=true");
		}
	}
}
