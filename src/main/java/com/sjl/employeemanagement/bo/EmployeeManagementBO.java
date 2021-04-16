package com.sjl.employeemanagement.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sjl.employeemanagement.dto.EmployeeDTO;
import com.sjl.employeemanagement.entities.Employee;
import com.sjl.employeemanagement.repository.EmployeeRepository;

@Component
public class EmployeeManagementBO {
	
	private final EmployeeRepository employeeRepository;
	
	EmployeeManagementBO (EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public List<EmployeeDTO> fetchAllEmployees() {
		List<Employee> listOfEmployees = employeeRepository.findAll();

		List<EmployeeDTO> listOfEmployeeDtos = new ArrayList<>();
		for (Employee e : listOfEmployees) {
			EmployeeDTO eDto = new EmployeeDTO();
			eDto.setEmail(e.getEmail());
			eDto.setFirstName(e.getFirstName());
			eDto.setId(e.getId());
			eDto.setLastName(e.getLastName());
			eDto.setMiddleName(e.getMiddleName());
			eDto.setPhoneNumber(e.getPhoneNumber());
			eDto.setPreferredName(e.getPreferredName());
			eDto.setRole(e.getRole());
			listOfEmployeeDtos.add(eDto);
		}

		return listOfEmployeeDtos;
	}
	
	public EmployeeDTO saveOrUpdateEmployee(EmployeeDTO employeeDto) {
		Employee employeeEntity = null;
		if(employeeDto.getId() == null || employeeRepository.findById(employeeDto.getId()) == null) {
			employeeEntity = new Employee();
		}else {
			employeeEntity = employeeRepository.findById(employeeDto.getId()).get();
		}
		employeeEntity.setEmail(employeeDto.getEmail());
		employeeEntity.setFirstName(employeeDto.getFirstName());
		employeeEntity.setLastName(employeeDto.getLastName());
		employeeEntity.setMiddleName(employeeDto.getMiddleName());
		employeeEntity.setPhoneNumber(employeeDto.getPhoneNumber());
		employeeEntity.setPreferredName(employeeDto.getPreferredName());
		employeeEntity.setRole(employeeDto.getRole());
		employeeEntity = employeeRepository.save(employeeEntity);
		employeeDto.setId(employeeEntity.getId());
		return employeeDto;
	}

}
