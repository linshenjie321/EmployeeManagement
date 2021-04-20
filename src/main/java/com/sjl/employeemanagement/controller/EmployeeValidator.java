package com.sjl.employeemanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.sjl.employeemanagement.dto.EmployeeDTO;

@Component
public class EmployeeValidator {
	
	public void validateEmployeeDTO (EmployeeDTO employeeDto) {
		List<String> messageList = new ArrayList<>();
		if (!StringUtils.hasLength(employeeDto.getFirstName())) {
			messageList.add("firstName is required");
		}
		if (!StringUtils.hasLength(employeeDto.getLastName())) {
			messageList.add("lastName is required");
		}
		if (!StringUtils.hasLength(employeeDto.getPhoneNumber()) && !StringUtils.hasLength(employeeDto.getEmail())) {
			messageList.add("either phoneNumber or email is required");
		}
		
		String errorMessage = "";
		if(!CollectionUtils.isEmpty(messageList)) {
			for(String msg : messageList) {
				errorMessage = errorMessage + msg + "; ";
			}
			throw new EmployeeDtoValidationException(errorMessage);
		}
	}

}
