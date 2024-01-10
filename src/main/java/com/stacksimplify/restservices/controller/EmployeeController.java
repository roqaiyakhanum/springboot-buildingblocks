package com.stacksimplify.restservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplify.restservices.entities.Employee;
import com.stacksimplify.restservices.exception.UserExistsException;
import com.stacksimplify.restservices.exception.UserNotFoundException;
import com.stacksimplify.restservices.services.EmployeeService;

@RestController
public class EmployeeController {
	
	
	@Autowired
	EmployeeService   employeeService;
	
	
	@GetMapping("/employee")
	public List<Employee>  getAllEmployee() {
		return employeeService.getAllEmployee();
		
	}
	
	@PostMapping("/createUser")
	public ResponseEntity<Void> createEmployee(@RequestBody Employee emp,UriComponentsBuilder builder) {
		
		try {
			 employeeService.createUser(emp);
			 
			 HttpHeaders headers= new HttpHeaders();
			 headers.setLocation(builder.path("/user/{id}").buildAndExpand(emp.getId()).toUri());
			 return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
			 
		} catch (UserExistsException e) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	
	@GetMapping("/user/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable("id") Long id) {
		
		try {
			return employeeService.getUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
		
	}
	@PutMapping("/user/{id}")
	public Employee updateEmployee(@RequestBody Employee emp,@PathVariable("id") Long id) {
		
		try {
			return employeeService.updateEmployeeById(emp, id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
		
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteEMployeeById(@PathVariable("id") Long id) {
		employeeService.deleteEmployee(id);
	}
	
	
    @GetMapping("/user/byUsername/{username}")
    public Employee findbyUsername(@PathVariable("username") String username) {
    	return employeeService.findbyEmployeename(username);
    }
}
