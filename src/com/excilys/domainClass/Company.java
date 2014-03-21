package com.excilys.domainClass;

public class Company {
	
	private int id;
	private String name;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param name the id to set
	 */
	public void setId(int id) {
		this.id = id;
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

	public Company() {
		super();
	}
	
	public Company(String name) {
		this.name = name;
	}
	
	public Company(String name, int id) {
		this.name = name;
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Company company) {
		return (company.getId() == this.getId());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}

	

}
