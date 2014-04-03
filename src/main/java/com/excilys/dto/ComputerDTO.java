package com.excilys.dto;


/**
 * this class represent the DTO of a computer
 * every field is represented with a simple type
 * id -> long
 * name, introduced, discontinued -> String
 * @author mbibos
 *
 */
public class ComputerDTO {
	private String id;
	private String name;
	private String introduced;
	private String discontinued;
	private String companyId;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
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
	
	/**
	 * @return the introduced
	 */
	public String getIntroduced() {
		return introduced;
	}
	
	/**
	 * @param introduced the introduced to set
	 */
	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}
	
	/**
	 * @return the discontinued
	 */
	public String getDiscontinued() {
		return discontinued;
	}
	
	/**
	 * @param discontinued the discontinued to set
	 */
	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}
	
	/**
	 * @return the company
	 */
	public String getCompanyId() {
		return companyId;
	}
	
	/**
	 * @param company the company to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public ComputerDTO(String id, String name, String introduced, String discontinued, String companyId) {
		this.id = id;
		this.companyId = companyId;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
	}
}