package com.ruuddeenen.plannerplus.services;

import com.ruuddeenen.plannerplus.models.Department;
import com.ruuddeenen.plannerplus.models.Employee;
import com.ruuddeenen.plannerplus.models.Shift;
import com.ruuddeenen.plannerplus.repositories.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    public List<Shift> getAllByDate(Date date){
        return shiftRepository.getAllByDate(date);
    }

    public List<Shift> getAllByEmployee(Employee employee){
        return shiftRepository.getAllByEmployee(employee);
    }

    public List<Shift> getAllByDepartment(Department department){
        return shiftRepository.getAllByDepartment(department);
    }
}
