package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.UserRepository;
import com.stacksimplify.restservices.entities.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	UserRepository userRepository;
	
	public List<Employee> getAllEmployee() {
		
		return userRepository.findAll();
		
	}
	
	
	public Employee createUser(Employee emp) {
		
		
		return userRepository.save(emp);
		
	}
	
	
	public Optional<Employee> getUserById(Long id) {
		
		Optional<Employee>  emp= userRepository.findById(id);
		
		return  emp;
		
	}
	
	
	public Employee updateEmployeeById(Employee emp,Long id) {
		
		emp.setId(id);
		return userRepository.save(emp);
		
	}
	
	public void deleteEmployee(Long id) {
		if(userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
			
		}
		
	}
	
	public Employee findbyEmployeename(String username) {
		return userRepository.findByUsername(username);
	}
	

}
