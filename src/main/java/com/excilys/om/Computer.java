package com.excilys.om;

import java.util.Date;

public class Computer {
	
	/**
	 * Class to represent a computer.
	 * A computer is represented by its id (unique), its name (legally unique), 
	 * its company (represented in DB by the company's id and its introduced and discontinued Date (can be null)
	 */
	private long id;
	private Company company;
	private String name;
	private Date introduced;
	private Date discontinued;
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
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

	public Computer() {
		super();
	}
	
	public Computer(Company company, String name, Date introduced, Date discontinued) {
		this.company = company;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
	}
	
	public Computer(long id, Company company, String name, Date introduced, Date discontinued) {
		this.id = id;
		this.company = company;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Computer computer) {
		return (this.getId() == computer.getId());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Computer [id=" + id + ", company=" + company + ", name=" + name
				+ ", introduced=" + introduced + ", discontinued="
				+ discontinued + "]";
	}

	
}
