package com.scotiabank.dosl.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scotiabank.dosl.userservice.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
