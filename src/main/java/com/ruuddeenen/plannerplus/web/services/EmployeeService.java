package com.ruuddeenen.plannerplus.web.services;

import com.ruuddeenen.plannerplus.web.exceptions.RecordNotFoundException;
import com.ruuddeenen.plannerplus.web.models.Employee;
import com.ruuddeenen.plannerplus.web.models.Shift;
import com.ruuddeenen.plannerplus.web.repositories.EmployeeRepository;
import com.ruuddeenen.plannerplus.web.repositories.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private ShiftRepository shiftRepository;
    private RecordNotFoundException recordNotFoundException = new RecordNotFoundException("No employee record exist for the given id");

    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = repository.findAll();
        if (employeeList.size() > 0) {
            return employeeList;
        } else return new ArrayList<>();
    }

    public Employee getEmployeeById(String id) throws RecordNotFoundException {
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException("No employee record exist for the given id"));
    }

    public boolean deleteEmployeeById(String id) throws RecordNotFoundException {
        Optional<Employee> employee = repository.findById(id);

        if (employee.isPresent()) {
            repository.deleteById(id);
            return true;
        } else {
            throw new RecordNotFoundException("No employee record exist for the given id");
        }
    }

    public Employee createOrUpdate(Employee entity) {
        entity = repository.save(entity);
        return entity;
    }

    public Employee addShift(String id, Shift shift) throws RecordNotFoundException {
        // todo
        Employee employee = repository.findById(id).orElseThrow(() -> recordNotFoundException);
        employee = repository.save(employee);
        return employee;
    }
/*
    public List<Employee> getAllByDepartments(List<Department> departments) throws RecordNotFoundException {
        List<Employee> employeeList = repository.getAllByDepartments(departments);
        if (employeeList.size() > 0){
            return employeeList;
        } else {
            throw new RecordNotFoundException("No employee records exist for the given department(s)");
        }
    }

 */
}
