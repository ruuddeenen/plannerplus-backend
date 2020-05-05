package com.ruuddeenen.plannerplus.web.repositories;


import com.ruuddeenen.plannerplus.web.models.Department;
import com.ruuddeenen.plannerplus.web.models.Employee;
import com.ruuddeenen.plannerplus.web.models.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
    List<Shift> findAllByEmployee(Employee employee);

    Optional<Shift> findByEmployeeAndDate(Employee employee, Date date);

    Optional<List<Shift>> findAllByDepartment(Department department);

    Optional<List<Shift>> findAllByDepartmentAndDate(Department department, Date date);
}
