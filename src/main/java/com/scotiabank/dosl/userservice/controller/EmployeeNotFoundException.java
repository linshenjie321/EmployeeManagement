package com.scotiabank.dosl.userservice.controller;

public class EmployeeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1663031723889173653L;

	public EmployeeNotFoundException(Long id) {
		super("Cound not find employee with the id " + id);
	}

}
