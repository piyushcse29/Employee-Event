package com.piyushmittal.employeeservice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.KafkaTemplate;

import com.piyushmittal.employeeservice.kafka.Event;

@SpringBootApplication
public class EmployeeServiceApplication implements CommandLineRunner {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	KafkaTemplate<String, Event> kafkaTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<String> hobbylist = new ArrayList<String>();
		hobbylist.add("Photography");
		hobbylist.add("Singing");
		
		
		employeeRepository.save(new Employee("m@piyushmittal.com","Piyush Mittal",LocalDate.of(1989, 2, 28),hobbylist));
		kafkaTemplate.send("employee-message",new Event("Event-Create","Employee updated with uuid "+1));
	}

}

