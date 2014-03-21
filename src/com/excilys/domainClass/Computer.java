package com.excilys.domainClass;

import java.util.Date;

public class Computer {
	
	private int id;
	private int companyId;
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
	 * @return the company_id
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * @param company_id the company_id to set
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
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
	
	public Computer(int id, int companyId, String name, Date introduced, Date discontinued) {
		this.companyId = companyId;
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
		return "Computer [id=" + id + ", companyId=" + companyId + ", name="
				+ name + ", introduced=" + introduced + ", discontinued="
				+ discontinued + "]";
	}

	

}
