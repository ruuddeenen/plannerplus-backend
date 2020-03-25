package com.ruuddeenen.plannerplus.repositories;


import com.ruuddeenen.plannerplus.models.Department;
import com.ruuddeenen.plannerplus.models.Employee;
import com.ruuddeenen.plannerplus.models.Shift;
import com.ruuddeenen.plannerplus.models.ShiftKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, ShiftKey> {

    List<Shift> getAllByDate(Date date);

    List<Shift> getAllByEmployee(Employee employee);

    List<Shift> getAllByDepartment(Department department);
}
