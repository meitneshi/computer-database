package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.excilys.om.Company;

public class CompanyResultSetExtractor implements ResultSetExtractor<Company> {

	@Override
	public Company extractData(ResultSet rs) throws SQLException, DataAccessException {

		Company company =  new Company();
		company.setId(rs.getLong("id"));
		company.setName(rs.getString("name"));
		
		return company;
	}	
}
