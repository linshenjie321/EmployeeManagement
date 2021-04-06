package com.scotiabank.dosl.userservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.scotiabank.dosl.userservice.entities.Employee;
import com.scotiabank.dosl.userservice.repository.EmployeeRepository;

@Configuration
public class LoadDatabase {
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	CommandLineRunner initDatabase(EmployeeRepository employeeRepository) {
		return args -> {
			log.info("Preloading " + employeeRepository.save(new Employee("Shen Jie", null, "Lin", "Shen",
					"123-456-7890", "sjl@sample.ca", Employee.Role.JAVA_SPRING_DEVELOPER)));
			log.info("Preloading " + employeeRepository.save(new Employee("Rahul", null, "Bhalla", null,
					"123-456-7890", "rb@sample.ca", Employee.Role.JAVA_SPRING_DEVELOPER)));
			log.info("Preloading " + employeeRepository.save(new Employee("Justin", null, "Tao", null,
					"123-456-7890", "jt@sample.ca", Employee.Role.JAVA_SPRING_DEVELOPER)));
		};
	}
}
