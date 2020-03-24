package com.ruuddeenen.plannerplus;

import com.ruuddeenen.plannerplus.models.Employee;
import com.ruuddeenen.plannerplus.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlannerplusApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlannerplusApplication.class, args);
	}

}
