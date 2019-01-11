package com.piyushmittal.employeeservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@GetMapping("/employees/{uuid}")
	Employee getEmployee(@PathVariable Long uuid) {
		return employeeRepository.findById(uuid).orElseThrow(()-> new EmployeeNotFoundException(uuid));
	}
	
	@GetMapping("/employees")
	List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}
	
	@PostMapping("/employees")
	Employee getEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@DeleteMapping("/employees/{uuid}")
	void deleteEmployee(@PathVariable Long uuid) {
		employeeRepository.deleteById(uuid);
	}
	
	@PutMapping("/employees/{uuid}")
	Employee updateEmployee(@RequestBody Employee updatedEmployee, @PathVariable Long uuid) {
		
		return employeeRepository.findById(uuid)
				.map(employee -> {
					employee.setEmail(updatedEmployee.getEmail());
					employee.setFullName(updatedEmployee.getFullName());
					employee.setBirthdate(updatedEmployee.getBirthdate());
					employee.setHobbies(updatedEmployee.getHobbies());
					return employeeRepository.save(employee);
				})
				.orElseGet(() -> {		
					return employeeRepository.save(updatedEmployee);
				});
		
	}
}
