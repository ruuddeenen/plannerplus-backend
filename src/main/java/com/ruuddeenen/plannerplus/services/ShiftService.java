package com.ruuddeenen.plannerplus.services;

import com.ruuddeenen.plannerplus.models.Employee;
import com.ruuddeenen.plannerplus.models.Shift;
import com.ruuddeenen.plannerplus.repositories.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    public List<Shift> getAll() {
        List<Shift> shiftList = shiftRepository.findAll();
        if (shiftList.size() > 0) {
            return shiftList;
        } else return new ArrayList<>();
    }

    public Shift createOrUpdate(Shift shift) {
        Optional<Shift> optional = shiftRepository.findByEmployeeAndDate(shift.getEmployee(), shift.getDate());
        if (optional.isPresent()) {
            Shift updated = optional.get();
            // TODO
            return updated;
        } else {
            Shift shift1 = shiftRepository.save(shift);
            return shift1;
        }
    }

    public List<Shift> getAllByEmployee(Employee employee){
        return shiftRepository.findAllByEmployee(employee);
    }


}
