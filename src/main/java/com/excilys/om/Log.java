package com.excilys.om;

import java.util.Date;

import org.springframework.stereotype.Component;

public class Log {

	/**
	 * Class to represent a Log.
	 * a log is represented by its id (unique), its date of generation (current_timestamp) and its label
	 * which is the message given by the code (computer created, computer updated, computer deleted....)
	 */
	private long id;
	private Date date;
	private String label;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	public Log() {
		super();
	}
	
	public Log(Date date, String label){
		this.date = date;
		this.label = label;
	}
}
