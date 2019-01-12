package com.piyushmittal.eventservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;

@Entity
@ApiModel(description="All details about the event.")
public class Event {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	Long id;
	private String message;
	private String description;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
