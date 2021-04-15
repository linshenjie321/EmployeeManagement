package com.sjl.employeemanagement.dto;

import org.springframework.hateoas.RepresentationModel;

import com.sjl.employeemanagement.entities.Employee;
import com.sjl.employeemanagement.entities.Employee.Role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "the Employee model which contains information about one particular employee")
public class EmployeeDTO extends RepresentationModel<Employee> {
	
	@ApiModelProperty(value = "the primary identifier of an employee")
	private Long id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String preferredName;
	private String phoneNumber;
	private String email;
	private Role role;
	
}
