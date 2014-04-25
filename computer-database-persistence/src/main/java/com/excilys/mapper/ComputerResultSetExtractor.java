package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.DateTime;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.excilys.om.Company;
import com.excilys.om.Computer;

public class ComputerResultSetExtractor implements ResultSetExtractor<Computer> {

	@Override
	public Computer extractData(ResultSet rs) throws SQLException, DataAccessException {

		Computer computer = new Computer();
		computer.setId(rs.getLong("id"));
		computer.setName(rs.getString("name"));
		
		computer.setIntroduced((rs.getTimestamp("introduced") == null) ? null : new DateTime(rs.getTimestamp("introduced")));
		computer.setDiscontinued((rs.getTimestamp("discontinued") == null) ? null : new DateTime(rs.getTimestamp("discontinued")));
		
		computer.setCompany(new Company(rs.getString("company.name"), rs.getInt("company_id")));
		return computer;
	}	
}
