package com.excilys.om;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table (name= "computer")
@XmlRootElement
public class Computer {

	/**
	 * Class to represent a computer.
	 * A computer is represented by its id (unique), its name (legally unique), 
	 * its company (represented in DB by the company's id and its introduced and discontinued Date (can be null)
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
	private Company company;
	
	@Column(name="name")
	private String name;
	
	@Column(name="introduced")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime introduced;
	
	@Column(name="discontinued")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime discontinued;

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
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
	public DateTime getIntroduced() {
		return introduced;
	}

	/**
	 * @param introduced the introduced to set
	 */
	public void setIntroduced(DateTime introduced) {
		this.introduced = introduced;
	}

	/**
	 * @return the discontinued
	 */
	public DateTime getDiscontinued() {
		return discontinued;
	}

	/**
	 * @param discontinued the discontinued to set
	 */
	public void setDiscontinued(DateTime discontinued) {
		this.discontinued = discontinued;
	}

	public Computer() {
		super();
	}

	public Computer(long id, Company company, String name, DateTime introduced, DateTime discontinued) {
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
