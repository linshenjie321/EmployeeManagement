package com.sjl.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sjl.employeemanagement.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
