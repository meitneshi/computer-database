package com.excilys.dao;

public interface ILogDAO {

	/**
	 * Create a entry in database in table 'log' with the message given in parameter 
	 * @param logMessage
	 */
	public void create (String logMessage);
}
