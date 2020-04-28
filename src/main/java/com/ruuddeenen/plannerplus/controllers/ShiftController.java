package com.ruuddeenen.plannerplus.controllers;

import com.ruuddeenen.plannerplus.exceptions.RecordNotFoundException;
import com.ruuddeenen.plannerplus.models.Employee;
import com.ruuddeenen.plannerplus.models.Shift;
import com.ruuddeenen.plannerplus.services.EmployeeService;
import com.ruuddeenen.plannerplus.services.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shifts")
public class ShiftController {

    @Autowired
    private ShiftService shiftService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Shift>> getAllShifts() {
        List<Shift> shiftList = shiftService.getAll();
        return new ResponseEntity<>(shiftList, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/employee/{id}")
    public ResponseEntity<Shift> createOrUpdateShift(
            @RequestBody Shift shift,
            @PathVariable("id") String employeeId
    ) throws RecordNotFoundException {
        shift.setEmployee(employeeService.getEmployeeById(employeeId));
        Shift updated = shiftService.createOrUpdate(shift);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<List<Shift>> getAllShiftsFromEmployeeId(
            @PathVariable("id") String employeeId
    ) throws RecordNotFoundException {
        Employee employee = employeeService.getEmployeeById(employeeId);
        List<Shift> shiftList = shiftService.getAllByEmployee(employee);
        return new ResponseEntity<>(shiftList, new HttpHeaders(), HttpStatus.OK);
    }
}
