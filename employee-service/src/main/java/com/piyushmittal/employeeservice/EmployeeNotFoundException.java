package com.piyushmittal.employeeservice;

public class EmployeeNotFoundException extends RuntimeException {

	EmployeeNotFoundException(Long uuid) {
		super("Could not find employee " + uuid);
	}
}
