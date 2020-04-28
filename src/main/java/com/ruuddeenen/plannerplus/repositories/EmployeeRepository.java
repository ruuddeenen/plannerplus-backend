package com.ruuddeenen.plannerplus.repositories;

import com.ruuddeenen.plannerplus.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Repository
@CrossOrigin("*")
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
