package com.scotiabank.dosl.userservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scotiabank.dosl.userservice.entities.Employee;
import com.scotiabank.dosl.userservice.repository.EmployeeRepository;

@RestController
public class EmployeeController {
	private final EmployeeRepository employeeRepository;

	EmployeeController(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@GetMapping("/employees")
	public List<Employee> all() {
		return employeeRepository.findAll();
	}

	@PostMapping("/employees")
	public Employee newEmployee(@RequestBody Employee newEmployee) {
		return employeeRepository.save(newEmployee);
	}

	@GetMapping("/employees/{id}")
	public Employee one(@PathVariable Long id) {
		return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}

	@PutMapping("/employees/{id}")
	public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		return employeeRepository.findById(id).map(employee -> {
			employee.setFirstName(newEmployee.getFirstName());
			employee.setEmail(newEmployee.getEmail());
			employee.setLastName(newEmployee.getLastName());
			employee.setMiddleName(newEmployee.getMiddleName());
			employee.setPhoneNumber(newEmployee.getPhoneNumber());
			employee.setPreferredName(newEmployee.getPreferredName());
			employee.setRole(newEmployee.getRole());
			return employeeRepository.save(employee);
		}).orElseGet(() -> {
			newEmployee.setId(id);
			return employeeRepository.save(newEmployee);
		});
	}

	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		employeeRepository.deleteById(id);
	}
}
