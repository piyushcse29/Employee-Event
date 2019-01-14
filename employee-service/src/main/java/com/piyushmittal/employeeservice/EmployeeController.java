package com.piyushmittal.employeeservice;

import static  org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
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
	Resource<Employee> getEmployee(@PathVariable Long uuid) {
		Optional<Employee> employee = employeeRepository.findById(uuid);
		
		if(!employee.isPresent())
		 throw new EmployeeNotFoundException(uuid);
		
		Resource<Employee> resource = new Resource<Employee>(employee.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllEmployee());
		resource.add(linkTo.withRel("all-employees"));
	
		return resource;
		
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
