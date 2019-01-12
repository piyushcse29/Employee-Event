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
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(description="All details about the employee.")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long uuid;
	
	//@Column(unique = true)
	//@Email
	private String email;
	
	@Size(min=3, message="Name should be atleast 3 character")
	@ApiModelProperty(notes="Name should be atleast 3 character")
	private String fullName;
	
	@ApiModelProperty(notes="Birth date should in the past")
	private LocalDate birthdate;
	
	@Column
    @ElementCollection(targetClass=String.class)
	private List<String> hobbies;
	
	Employee(){}
	
	public Employee(String email, String fullName, LocalDate birthday, List<String> hobbies){
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
