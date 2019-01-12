package com.piyushmittal.employeeservice.exception;

public class EmployeeNotFoundException extends RuntimeException {

	public EmployeeNotFoundException(Long uuid) {
		super("Could not find employee " + uuid);
	}
}
