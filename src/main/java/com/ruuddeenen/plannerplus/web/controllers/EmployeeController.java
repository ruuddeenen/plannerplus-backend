package com.ruuddeenen.plannerplus.web.controllers;

import com.ruuddeenen.plannerplus.web.assemblers.EmployeeResourceAssembler;
import com.ruuddeenen.plannerplus.web.exceptions.RecordNotFoundException;
import com.ruuddeenen.plannerplus.web.models.Employee;
import com.ruuddeenen.plannerplus.web.services.EmployeeService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;
    private EmployeeResourceAssembler assembler;

    EmployeeController(
            EmployeeResourceAssembler assembler,
            EmployeeService employeeService
    ) {
        this.assembler = assembler;
        this.employeeService = employeeService;
    }

    @GetMapping
    public CollectionModel<EntityModel<Employee>> getAllEmployees() {
        List<EntityModel<Employee>> employees = employeeService.getAllEmployees()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(employees,
                linkTo(methodOn(EmployeeController.class).getAllEmployees()).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    public EntityModel<Employee> getEmployeeById(@PathVariable("id") String id) throws RecordNotFoundException {
        Employee employee = employeeService.getEmployeeById(id);
        return assembler.toModel(employee);
    }

    @PostMapping
    public EntityModel<Employee> createOrUpdateEmployee(@RequestBody Employee employee) {
        Employee updated = employeeService.createOrUpdate(employee);
        return assembler.toModel(updated);
    }

    @DeleteMapping("/{id}")
    public CollectionModel deleteEmployeeById(@PathVariable("id") String id) throws RecordNotFoundException {
        if (employeeService.deleteEmployeeById(id)) {
            List<EntityModel<Employee>> employees = employeeService.getAllEmployees()
                    .stream()
                    .map(assembler::toModel)
                    .collect(Collectors.toList());
            return new CollectionModel<>(employees,
                    linkTo(methodOn(EmployeeController.class).getAllEmployees()).withSelfRel()
            );
        }
        return null;
    }
}
