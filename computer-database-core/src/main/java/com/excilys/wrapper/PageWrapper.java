package com.excilys.wrapper;

import java.util.List;

import com.excilys.om.Computer;

public class PageWrapper {

	private int entitiesPerPage;
	private int currentPagenumber;
	private long numberOfComputer;
	private long numberTotalOfComputer;
	private int pageMax;
	
	private String criteria;
	private String order;
	private String filter;
	
	private String add;
	private String edit;
	private String delete;
	
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
	public long getNumberOfComputer() {
		return numberOfComputer;
	}

	/**
	 * @param l the numberOfComputer to set
	 */
	public void setNumberOfComputer(long l) {
		this.numberOfComputer = l;
	}

	/**
	 * @return the numberTotalOfComputer
	 */
	public long getNumberTotalOfComputer() {
		return numberTotalOfComputer;
	}

	/**
	 * @param l the numberTotalOfComputer to set
	 */
	public void setNumberTotalOfComputer(long l) {
		this.numberTotalOfComputer = l;
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
	 * @return the add
	 */
	public String getAdd() {
		return add;
	}

	/**
	 * @param add the add to set
	 */
	public void setAdd(String add) {
		this.add = add;
	}

	/**
	 * @return the edit
	 */
	public String getEdit() {
		return edit;
	}

	/**
	 * @param edit the edit to set
	 */
	public void setEdit(String edit) {
		this.edit = edit;
	}

	/**
	 * @return the delete
	 */
	public String getDelete() {
		return delete;
	}

	/**
	 * @param delete the delete to set
	 */
	public void setDelete(String delete) {
		this.delete = delete;
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
			String add, String edit, String delete,
			List<Computer> computerPageList) {
		super();
		this.entitiesPerPage = entitiesPerPage;
		this.currentPagenumber = currentPagenumber;
		this.numberOfComputer = numberOfComputer;
		this.numberTotalOfComputer = numberTotalOfComputer;
		this.pageMax = pageMax;
		this.add = add;
		this.edit = edit;
		this.delete = delete;
		this.criteria = criteria;
		this.order = order;
		this.filter = filter;
		this.computerPageList = computerPageList;
	}	
}
