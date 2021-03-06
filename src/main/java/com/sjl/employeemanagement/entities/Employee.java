package com.sjl.employeemanagement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@ApiModel(description = "the Employee model which contains information about one particular employee")
public class Employee extends RepresentationModel<Employee> {
	
	@ApiModelProperty(value = "the primary identifier of an employee")
	private @Id @GeneratedValue Long id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String preferredName;
	private String phoneNumber;
	private String email;
	private Role role;

	public static enum Role {
		PEGA_DEVELOPER, JAVA_SPRING_DEVELOPER, BUSINESS_ANALYST, AUTOMATION_QA, MANUAL_QA
	}

	public Employee(String firstName, String middleName, String lastName, String preferredName, String phoneNumber,
			String email, Role role) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.preferredName = preferredName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.role = role;
	}

}
