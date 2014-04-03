package com.excilys.wrapper;

import java.util.List;

import com.excilys.om.Computer;

public class PageWrapper {

	private int entitiesPerPage;
	private int currentPagenumber;
	private int numberOfComputer;
	private int numberTotalOfComputer;
	private int pageMax;
	
	private String criteria;
	private String order;
	private String filter;
	
	private List<Computer> computerPageList;

	/**
	 * @return the entitiesPerPage
	 */
	public int getEntitiesPerPage() {
		return entitiesPerPage;
	}

	/**
	 * @param entitiesPerPage the entitiesPerPage to set
	 */
	public void setEntitiesPerPage(int entitiesPerPage) {
		this.entitiesPerPage = entitiesPerPage;
	}

	/**
	 * @return the currentPagenumber
	 */
	public int getCurrentPagenumber() {
		return currentPagenumber;
	}

	/**
	 * @param currentPagenumber the currentPagenumber to set
	 */
	public void setCurrentPagenumber(int currentPagenumber) {
		this.currentPagenumber = currentPagenumber;
	}

	/**
	 * @return the numberOfComputer
	 */
	public int getNumberOfComputer() {
		return numberOfComputer;
	}

	/**
	 * @param numberOfComputer the numberOfComputer to set
	 */
	public void setNumberOfComputer(int numberOfComputer) {
		this.numberOfComputer = numberOfComputer;
	}

	/**
	 * @return the numberTotalOfComputer
	 */
	public int getNumberTotalOfComputer() {
		return numberTotalOfComputer;
	}

	/**
	 * @param numberTotalOfComputer the numberTotalOfComputer to set
	 */
	public void setNumberTotalOfComputer(int numberTotalOfComputer) {
		this.numberTotalOfComputer = numberTotalOfComputer;
	}

	/**
	 * @return the pageMax
	 */
	public int getPageMax() {
		return pageMax;
	}

	/**
	 * @param pageMax the pageMax to set
	 */
	public void setPageMax(int pageMax) {
		this.pageMax = pageMax;
	}

	/**
	 * @return the criteria
	 */
	public String getCriteria() {
		return criteria;
	}

	/**
	 * @param criteria the criteria to set
	 */
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	/**
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * @return the filter
	 */
	public String getFilter() {
		return filter;
	}

	/**
	 * @param filter the filter to set
	 */
	public void setFilter(String filter) {
		this.filter = filter;
	}

	/**
	 * @return the computerPageList
	 */
	public List<Computer> getComputerPageList() {
		return computerPageList;
	}

	/**
	 * @param computerPageList the computerPageList to set
	 */
	public void setComputerPageList(List<Computer> computerPageList) {
		this.computerPageList = computerPageList;
	}

	public PageWrapper() {
		super();
	}
	
	public PageWrapper(int entitiesPerPage, int currentPagenumber,
			int numberOfComputer, int numberTotalOfComputer, int pageMax,
			String criteria, String order, String filter,
			List<Computer> computerPageList) {
		super();
		this.entitiesPerPage = entitiesPerPage;
		this.currentPagenumber = currentPagenumber;
		this.numberOfComputer = numberOfComputer;
		this.numberTotalOfComputer = numberTotalOfComputer;
		this.pageMax = pageMax;
		this.criteria = criteria;
		this.order = order;
		this.filter = filter;
		this.computerPageList = computerPageList;
	}
	
	
	
	
}
