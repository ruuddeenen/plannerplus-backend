package com.ruuddeenen.plannerplus.web.repositories;

import com.ruuddeenen.plannerplus.web.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin("*")
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
