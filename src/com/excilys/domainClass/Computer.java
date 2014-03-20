package com.excilys.domainClass;

import java.util.Date;

public class Computer {
	
	private int id;
	private int company_id;
	private String name;
	private Date introduced;
	private Date discontinued;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the company_id
	 */
	public int getCompany_id() {
		return company_id;
	}

	/**
	 * @param company_id the company_id to set
	 */
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the introduced
	 */
	public Date getIntroduced() {
		return introduced;
	}

	/**
	 * @param introduced the introduced to set
	 */
	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	/**
	 * @return the discontinued
	 */
	public Date getDiscontinued() {
		return discontinued;
	}

	/**
	 * @param discontinued the discontinued to set
	 */
	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}

	public Computer(int id, int company_id, String name, Date introduced, Date discontinued) {
		this.id = id;
		this.company_id = company_id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
	}

}
