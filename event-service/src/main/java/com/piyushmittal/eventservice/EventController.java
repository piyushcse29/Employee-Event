package com.piyushmittal.eventservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {
	
	@Autowired
	EventRepository eventRepository;
	
	@GetMapping("/events")
	List<Event> getAllEvents(){
		return eventRepository.findAll();
		
	}
	
	void saveEvent(Event event) {
		eventRepository.save(event);
	}
}
