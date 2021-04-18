package com.sjl.employeemanagement.controller;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.sjl.employeemanagement.dto.EmployeeDTO;

@Component
public class EmployeeValidator {
	
	public void validateEmployeeDTO (EmployeeDTO employeeDto) {
		if (!StringUtils.hasLength(employeeDto.getFirstName())) {
			throw new EmployeeDtoValidationException("firstName");
		}
	}

}
