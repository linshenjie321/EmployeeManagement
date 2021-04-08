package com.sjl.employeemanagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjl.employeemanagement.entities.Employee;
import com.sjl.employeemanagement.repository.EmployeeRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	private final EmployeeRepository employeeRepository;

	EmployeeController(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@GetMapping("/")
	@ApiOperation(value = "creates a new employee")
	public List<Employee> all() {
		return employeeRepository.findAll();
	}

	@PostMapping("/")
	@ApiOperation(value = "creates a new employee",
		response =Employee.class)
	public Employee newEmployee(@RequestBody Employee newEmployee) {
		return employeeRepository.save(newEmployee);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "returns one employee by the employee ID",
			response =Employee.class)
	public Employee one(@PathVariable Long id) {
		return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "updates an existing employee, if employee does not exist, it will create one",
		response =Employee.class)
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

	@DeleteMapping("/{id}")
	@ApiOperation(value = "deletes an existing employee")
	public void deleteEmployee(@PathVariable Long id) {
		employeeRepository.deleteById(id);
	}
}
