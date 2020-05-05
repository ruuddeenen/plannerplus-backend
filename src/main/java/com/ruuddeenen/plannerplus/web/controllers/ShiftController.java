package com.ruuddeenen.plannerplus.web.controllers;

import com.ruuddeenen.plannerplus.web.assemblers.ShiftResourceAssembler;
import com.ruuddeenen.plannerplus.web.exceptions.RecordNotFoundException;
import com.ruuddeenen.plannerplus.web.models.Department;
import com.ruuddeenen.plannerplus.web.models.Employee;
import com.ruuddeenen.plannerplus.web.models.Shift;
import com.ruuddeenen.plannerplus.web.services.DepartmentService;
import com.ruuddeenen.plannerplus.web.services.EmployeeService;
import com.ruuddeenen.plannerplus.web.services.ShiftService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/shifts")
public class ShiftController {

    private ShiftResourceAssembler assembler;
    private ShiftService shiftService;
    private EmployeeService employeeService;
    private DepartmentService departmentService;

    public ShiftController(
            ShiftResourceAssembler assembler,
            ShiftService shiftService,
            EmployeeService employeeService,
            DepartmentService departmentService
    ) {
        this.assembler = assembler;
        this.shiftService = shiftService;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }


    @GetMapping("/{id}")
    public EntityModel<Shift> getShiftById(@PathVariable Long id) throws RecordNotFoundException {
        Shift shift = shiftService.getById(id);
        return assembler.toModel(shift);
    }

    @GetMapping("/dep/{id}")
    public CollectionModel<EntityModel<Shift>> getShiftByDepartment(
            @PathVariable Long id,
            @RequestParam(required = false) Long ts) throws RecordNotFoundException {
        Department department = departmentService.getDepartmentById(id);

        List<EntityModel<Shift>> shifts;
        if (ts == null) {
            shifts = shiftService.getAllByDepartment(department)
                    .stream()
                    .map(assembler::toModel)
                    .collect(Collectors.toList());
        } else {
            Date date = new Date(ts);
            shifts = shiftService.getAllByDepartmentAndDate(department, date)
                    .stream()
                    .map(assembler::toModel)
                    .collect(Collectors.toList());
        }
        return new CollectionModel<>(shifts,
                linkTo(methodOn(ShiftController.class).getShiftByDepartment(id, ts)).withSelfRel());
    }

    /*
    @GetMapping
    public ResponseEntity<List<Shift>> getAllShifts(
            @RequestParam String employeeId,
            @RequestParam Long departmentId
    ) {
        List<Shift> shiftList = shiftService.getAll();
        return new ResponseEntity<>(shiftList, new HttpHeaders(), HttpStatus.OK);
    }
     */

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
            @PathVariable String id) throws RecordNotFoundException {
        Employee employee = employeeService.getEmployeeById(id);
        List<Shift> shiftList = shiftService.getAllByEmployee(employee);
        return new ResponseEntity<>(shiftList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping
    public CollectionModel<EntityModel<Shift>> getAllShifts() {
        List<EntityModel<Shift>> shifts = shiftService.getAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());


        return new CollectionModel<>(shifts,
                linkTo(methodOn(ShiftController.class).getAllShifts()).withSelfRel());
    }
}
