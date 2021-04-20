package com.sjl.employeemanagement.controller;

public class EmployeeDtoValidationException extends RuntimeException {
	
	private static final long serialVersionUID = 898462009545223311L;
	
	public EmployeeDtoValidationException (String errorMessage) {
		super(errorMessage);
	}

}
