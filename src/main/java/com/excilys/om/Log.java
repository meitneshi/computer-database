package com.excilys.om;

import org.joda.time.DateTime;

public class Log {

	/**
	 * Class to represent a Log.
	 * a log is represented by its id (unique), its date of generation (current_timestamp) and its label
	 * which is the message given by the code (computer created, computer updated, computer deleted....)
	 */
	private long id;
	private DateTime date;
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
	public DateTime getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(DateTime date) {
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
	
	public Log(DateTime date, String label){
		this.date = date;
		this.label = label;
	}
}
