package com.ruuddeenen.plannerplus.repositories;


import com.ruuddeenen.plannerplus.models.Employee;
import com.ruuddeenen.plannerplus.models.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
    List<Shift> findAllByEmployee(Employee employee);
    Optional<Shift> findByEmployeeAndDate(Employee employee, String date);
    List<Shift> findAllByDate(String date);
}
