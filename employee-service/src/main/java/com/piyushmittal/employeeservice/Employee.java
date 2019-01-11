package com.piyushmittal.employeeservice;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;



@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long uuid;
	
	@Column(unique = true)
	@Email
	private String email;
	private String fullName;
	private LocalDate birthdate;
	
	@Column
    @ElementCollection(targetClass=String.class)
	private List<String> hobbies;
	
	Employee(){}
	
	Employee(String email, String fullName, LocalDate birthday, List<String> hobbies){
		this.email = email;
		this.fullName = fullName;
		this.birthdate = birthday;
		this.birthdate = birthdate;
		this.hobbies = hobbies;
	}
	
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public List<String> getHobbies() {
		return hobbies;
	}
	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}

}
