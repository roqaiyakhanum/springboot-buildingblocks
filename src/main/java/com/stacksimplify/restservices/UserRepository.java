package com.stacksimplify.restservices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stacksimplify.restservices.entities.Employee;

public interface UserRepository extends JpaRepository<Employee, Long> {
	
	
	public Employee findByUsername(String username);
	

}
