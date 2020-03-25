package com.ruuddeenen.plannerplus.repositories;

import com.ruuddeenen.plannerplus.models.Department;
import com.ruuddeenen.plannerplus.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
