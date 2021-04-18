package com.sjl.employeemanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EmployeeDtoErrorAdvice {
	
	@ResponseBody
	@ExceptionHandler(EmployeeDtoValidationException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public String employeeDtoValidationHandler(EmployeeDtoValidationException ex) {
		return ex.getMessage();
	}

}
