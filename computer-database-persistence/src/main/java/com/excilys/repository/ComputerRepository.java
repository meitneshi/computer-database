package com.excilys.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.om.Computer;

public interface ComputerRepository extends JpaRepository<Computer, Long>{
	
	List<Computer> findByNameContaining(String name, Pageable page);
	
	long countByNameContaining(String pattern);
}
