package com.excilys.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.om.Computer;

public interface ComputerRepository extends JpaRepository<Computer, Long>{
	
	List<Computer> findByNameContainingOrCompanyName(String name, String companyName, Pageable page);
	
	long countByNameContaining(String pattern);
}
