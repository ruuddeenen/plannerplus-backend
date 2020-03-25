package com.ruuddeenen.plannerplus.controllers;

import com.ruuddeenen.plannerplus.exceptions.RecordNotFoundException;
import com.ruuddeenen.plannerplus.models.Department;
import com.ruuddeenen.plannerplus.models.Employee;
import com.ruuddeenen.plannerplus.services.DepartmentService;
import com.ruuddeenen.plannerplus.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService service;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departmentList = service.getAllDepartments();
        return new ResponseEntity<>(departmentList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Long id) throws RecordNotFoundException {
        Department department = service.getDepartmentById(id);
        return new ResponseEntity<>(department, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/{id}/add")
    public ResponseEntity<Department> addEmployee(@PathVariable Long id, @RequestParam Long employeeId) throws RecordNotFoundException {
        Employee employee = employeeService.getEmployeeById(employeeId);
        Department updated = service.addEmployeeToDepartment(employee, id);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/remove")
    public ResponseEntity<Department> removeEmployee(@PathVariable Long id, @RequestParam Long employeeId) throws RecordNotFoundException {
        Employee employee = employeeService.getEmployeeById(employeeId);
        Department updated = service.removeEmployeeFromDepartment(employee, id);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Department> createOrUpdateDepartment(@RequestBody Department department) {
        Department updated = service.createOrUpdate(department);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public HttpStatus deleteDepartmentById(@PathVariable("id") Long id) throws RecordNotFoundException {
        service.deleteDepartmentById(id);
        return HttpStatus.FORBIDDEN;
    }


}
