package com.sjl.employeemanagement.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;

import javax.validation.Valid;

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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:3000")
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class EmployeeController {
	
	@Autowired
	private EmployeeManagementBO employeeManagementBO;
	
	@Autowired
	private EmployeeValidator employeeValidator;

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
		response =EmployeeDTO.class)
	public EmployeeDTO newEmployee(@Valid @RequestBody EmployeeDTO newEmployee) {
		employeeValidator.validateEmployeeDTO(newEmployee);
		return employeeManagementBO.saveOrUpdateEmployee(newEmployee);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "returns one employee by the employee ID",
			response =EmployeeDTO.class)
	public EmployeeDTO one(@ApiParam(value = "the id of the employee", required = true) @PathVariable Long id) {
		return employeeManagementBO.findById(id);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "updates an existing employee, if employee does not exist, it will create one",
		response =EmployeeDTO.class)
	public EmployeeDTO replaceEmployee(@RequestBody EmployeeDTO employee, @PathVariable Long id) {
		employee.setId(id);
		return employeeManagementBO.saveOrUpdateEmployee(employee);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "deletes an existing employee")
	public void deleteEmployee(@PathVariable Long id) {
		employeeManagementBO.deleteEmployee(id);
	}
}
