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
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ShiftService shiftService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employeeList = employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") String id) throws RecordNotFoundException {
        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> createOrUpdateEmployee(@RequestBody Employee employee) {
        Employee updated = employeeService.createOrUpdate(employee);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteEmployeeById(@PathVariable("id") String id) throws RecordNotFoundException {
        employeeService.deleteEmployeeById(id);
        return HttpStatus.FORBIDDEN;
    }

    @PostMapping("/{id}/addShift")
    public ResponseEntity<Shift> addShift(
            @PathVariable("id") String id,
            @RequestBody Shift body) throws RecordNotFoundException {
        Employee employee = employeeService.getEmployeeById(id);
        body.setEmployee(employee);
        Shift shift = shiftService.createOrUpdate(body);
        employeeService.addShift(id, shift);
        return new ResponseEntity<>(shift, new HttpHeaders(), HttpStatus.OK);
    }
}
