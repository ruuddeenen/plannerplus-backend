package com.ruuddeenen.plannerplus.web.controllers;

import com.ruuddeenen.plannerplus.web.assemblers.DepartmentResourceAssembler;
import com.ruuddeenen.plannerplus.web.exceptions.RecordNotFoundException;
import com.ruuddeenen.plannerplus.web.models.Department;
import com.ruuddeenen.plannerplus.web.models.Employee;
import com.ruuddeenen.plannerplus.web.services.DepartmentService;
import com.ruuddeenen.plannerplus.web.services.EmployeeService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private DepartmentResourceAssembler assembler;
    private DepartmentService departmentService;
    private EmployeeService employeeService;

    DepartmentController(
            DepartmentResourceAssembler assembler,
            DepartmentService departmentService,
            EmployeeService employeeService
    ){
        this.assembler = assembler;
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public CollectionModel<EntityModel<Department>> getAllDepartments() {
        List<EntityModel<Department>> departments = departmentService.getAllDepartments()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(departments,
                linkTo(methodOn(DepartmentController.class).getAllDepartments()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Department> getDepartmentById(@PathVariable("id") Long id) throws RecordNotFoundException {
        Department department = departmentService.getDepartmentById(id);
        return assembler.toModel(department);
    }

    @PostMapping("/{id}/add")
    public ResponseEntity<Department> addEmployee(
            @PathVariable Long id,
            @RequestParam String employeeId
    ) throws RecordNotFoundException {
        Employee employee = employeeService.getEmployeeById(employeeId);
        Department updated = departmentService.addEmployeeToDepartment(employee, id);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/remove")
    public ResponseEntity<Department> removeEmployee(
            @PathVariable Long id,
            @RequestParam String employeeId
    ) throws RecordNotFoundException {
        Employee employee = employeeService.getEmployeeById(employeeId);
        Department updated = departmentService.removeEmployeeFromDepartment(employee, id);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Department> createOrUpdateDepartment(@RequestBody Department department) {
        Department updated = departmentService.createOrUpdate(department);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public HttpStatus deleteDepartmentById(
            @PathVariable("id") Long id
    ) throws RecordNotFoundException {
        departmentService.deleteDepartmentById(id);
        return HttpStatus.FORBIDDEN;
    }


}
