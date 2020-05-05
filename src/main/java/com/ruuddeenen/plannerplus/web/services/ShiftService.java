package com.ruuddeenen.plannerplus.web.services;

import com.ruuddeenen.plannerplus.web.exceptions.RecordNotFoundException;
import com.ruuddeenen.plannerplus.web.models.Department;
import com.ruuddeenen.plannerplus.web.models.Employee;
import com.ruuddeenen.plannerplus.web.models.Shift;
import com.ruuddeenen.plannerplus.web.repositories.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShiftService {

    @Autowired
    private ShiftRepository repo;

    public List<Shift> getAll() {
        List<Shift> shiftList = repo.findAll();
        if (shiftList.size() > 0) {
            return shiftList;
        } else return new ArrayList<>();
    }

    public Shift createOrUpdate(Shift shift) {
        Optional<Shift> optional = repo.findByEmployeeAndDate(shift.getEmployee(), shift.getDate());
        if (optional.isPresent()) {
            Shift updated = optional.get();
            // TODO
            return updated;
        } else {
            Shift shift1 = repo.save(shift);
            return shift1;
        }
    }

    public List<Shift> getAllByEmployee(Employee employee){
        return repo.findAllByEmployee(employee);
    }

    public List<Shift> getAllByDepartmentAndDate(Department department, Date date){
        Optional<List<Shift>> optional = repo.findAllByDepartmentAndDate(department, date);
        return optional.orElseGet(ArrayList::new);
    }

    public List<Shift> getAllByDepartment(Department department){
        Optional<List<Shift>> optional = repo.findAllByDepartment(department);
        return optional.orElseGet(ArrayList::new);
    }


    public Shift getById(Long id) throws RecordNotFoundException {
        return repo.findById(id).orElseThrow(() -> new RecordNotFoundException("No employee record exist for the given id"));
    }
}
