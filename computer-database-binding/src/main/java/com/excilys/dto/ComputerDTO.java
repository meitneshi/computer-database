package com.excilys.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.excilys.validator.annotations.DateValid;
import com.excilys.validator.annotations.NameValid;

/**
 * this class represent the DTO of a computer
 * every field is represented with a simple type
 * id -> long
 * name, introduced, discontinued -> String
 * @author mbibos toto
 *
 */
public class ComputerDTO {
	
	@NotNull
	private String id;
	
	@NotEmpty
	@Size(min = 2, max = 255)
	@NameValid
	private String name;
	
	@DateValid
	private String introduced;
	
	@DateValid
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
	
	public ComputerDTO() {
		super();
	}

	public ComputerDTO(String id, String name, String introduced, String discontinued, String companyId) {
		this.id = id;
		this.companyId = companyId;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ComputerDTO [id=" + id + ", name=" + name + ", introduced="
				+ introduced + ", discontinued=" + discontinued
				+ ", companyId=" + companyId + "]";
	}
}
