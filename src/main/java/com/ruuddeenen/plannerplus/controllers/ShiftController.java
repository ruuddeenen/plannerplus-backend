package com.ruuddeenen.plannerplus.controllers;

import com.ruuddeenen.plannerplus.exceptions.RecordNotFoundException;
import com.ruuddeenen.plannerplus.models.Employee;
import com.ruuddeenen.plannerplus.models.Shift;
import com.ruuddeenen.plannerplus.services.DepartmentService;
import com.ruuddeenen.plannerplus.services.EmployeeService;
import com.ruuddeenen.plannerplus.services.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shifts")
public class ShiftController {

    @Autowired
    private ShiftService shiftService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Shift>> getAllShifts() {
        List<Shift> shiftList = shiftService.getAll();
        return new ResponseEntity<>(shiftList, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Shift> createOrUpdateShift(
            @RequestBody Shift shift,
            @RequestParam String employeeId,
            @RequestParam Long departmentId
    ) throws RecordNotFoundException {
        shift.setEmployee(employeeService.getEmployeeById(employeeId));
        shift.setDepartment(departmentService.getDepartmentById(departmentId));
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
