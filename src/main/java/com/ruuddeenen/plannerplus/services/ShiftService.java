package com.ruuddeenen.plannerplus.services;

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

    public List<Shift> getByDay(Date day){
        return shiftRepository.getByDay(day);
    }
}
