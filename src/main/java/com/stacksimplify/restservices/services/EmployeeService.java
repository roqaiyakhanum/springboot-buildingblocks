package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.UserRepository;
import com.stacksimplify.restservices.entities.Employee;
import com.stacksimplify.restservices.exception.UserExistsException;
import com.stacksimplify.restservices.exception.UserNotFoundException;

@Service
public class EmployeeService {
	
	@Autowired
	UserRepository userRepository;
	
	public List<Employee> getAllEmployee() {
		
		return userRepository.findAll();
		
	}
	
	
	public Employee createUser(Employee emp) throws UserExistsException{
		
		Employee existingUSer=userRepository.findByUsername(emp.getUsername());
		if(existingUSer!=null) {
			
			throw new UserExistsException("user already exists in repo");
			
		}
		
		
		return userRepository.save(emp);
		
	}
	
	
	public Optional<Employee> getUserById(Long id) throws UserNotFoundException {
		
		Optional<Employee>  emp= userRepository.findById(id);
		if(!emp.isPresent()) {
			throw new UserNotFoundException("Employee not found in repository");
			
		}
		
		return  emp;
		
	}
	
	
	public Employee updateEmployeeById(Employee emp,Long id) throws UserNotFoundException {
		Optional<Employee>  empOP= userRepository.findById(id);
		if(!empOP.isPresent()) {
			throw new UserNotFoundException("Employee not found in repository");
			
		}
		
		emp.setId(id);
		return userRepository.save(emp);
		
	}
	
	public void deleteEmployee(Long id) {
		
		Optional<Employee>  emp= userRepository.findById(id);
		if(!emp.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"user not found");
			
		}
		/*
		 * if(userRepository.findById(id).isPresent()) { userRepository.deleteById(id);
		 * 
		 * }
		 */
		
		userRepository.deleteById(id);
	}
	
	public Employee findbyEmployeename(String username) {
		return userRepository.findByUsername(username);
	}
	

}
