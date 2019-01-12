package com.piyushmittal.eventservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.piyushmittal.eventservice.Event;
import com.piyushmittal.eventservice.EventRepository;

@Service
public class EventListener {
	
	@Autowired
	EventRepository eventRepository;

	@KafkaListener(topics = "employee-message", groupId = "group_json", containerFactory = "eventKafkaListenerFactory")
	public void consumeJson(Event event) {
		
		eventRepository.save(event);
		
		System.out.println("Consumed JSON Message: " + event.getDescription()+" "+event.getMessage());
	}
}
