package com.sjl.employeemanagement.controller;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjl.employeemanagement.bo.EmployeeManagementBO;
import com.sjl.employeemanagement.dto.EmployeeDTO;
import com.sjl.employeemanagement.entities.Employee;
import com.sjl.employeemanagement.repository.EmployeeRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:3000")
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class EmployeeController {
	
	private final EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeManagementBO employeeManagementBO;

	EmployeeController(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@GetMapping("/")
	@ApiOperation(value = "creates a new employee")
	public CollectionModel<EmployeeDTO> all() {
		Link link = linkTo(EmployeeController.class).withSelfRel();
		List<EmployeeDTO> employees = employeeManagementBO.fetchAllEmployees();
		CollectionModel<EmployeeDTO> result = CollectionModel.of(employees, link);
		return result;
	}

	@PostMapping("/")
	@ApiOperation(value = "creates a new employee",
		response =Employee.class)
	public EmployeeDTO newEmployee(@RequestBody EmployeeDTO newEmployee) {
		return employeeManagementBO.saveOrUpdateEmployee(newEmployee);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "returns one employee by the employee ID",
			response =Employee.class)
	public Employee one(@ApiParam(value = "the id of the employee", required = true) @PathVariable Long id) {
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
		employeeManagementBO.deleteEmployee(id);
	}
}
