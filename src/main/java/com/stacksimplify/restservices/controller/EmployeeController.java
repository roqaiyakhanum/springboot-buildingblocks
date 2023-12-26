package com.stacksimplify.restservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.Employee;
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
	public Employee createEmployee(@RequestBody Employee emp) {
		
		return employeeService.createUser(emp);
	}
	
	
	@GetMapping("/user/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable("id") Long id) {
		
		return employeeService.getUserById(id);
		
	}
	@PutMapping("/user/{id}")
	public Employee updateEmployee(@RequestBody Employee emp,@PathVariable("id") Long id) {
		
		return employeeService.updateEmployeeById(emp, id);
		
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
