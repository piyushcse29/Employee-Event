package com.piyushmittal.employeeservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.piyushmittal.employeeservice.exception.EmployeeNotFoundException;
import com.piyushmittal.employeeservice.kafka.Event;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	KafkaTemplate<String, Event> kafkaTemplate;
	
	@GetMapping("/employees/{uuid}")
	Employee getEmployee(@PathVariable Long uuid) {
		return employeeRepository.findById(uuid).orElseThrow(()-> new EmployeeNotFoundException(uuid));
	}
	
	@GetMapping("/employees")
	List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}
	
	@PostMapping("/employees")
	Employee saveEmployee(@RequestBody Employee employee) {
		kafkaTemplate.send("employee-message",new Event("Event-Create","Employee created with email "+employee.getEmail()));
		return employeeRepository.save(employee);
	}
	
	@DeleteMapping("/employees/{uuid}")
	void deleteEmployee(@PathVariable Long uuid) {
		kafkaTemplate.send("employee-message",new Event("Event-Delete","Employee deleted with uuid "+uuid));
		employeeRepository.deleteById(uuid);
	}
	
	@PutMapping("/employees/{uuid}")
	Employee updateEmployee(@RequestBody Employee updatedEmployee, @PathVariable Long uuid) {
		
		kafkaTemplate.send("employee-message",new Event("Event-Create","Employee updated with uuid "+uuid));
		
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
