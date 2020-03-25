package com.ruuddeenen.plannerplus.services;

import com.ruuddeenen.plannerplus.exceptions.RecordNotFoundException;
import com.ruuddeenen.plannerplus.models.Department;
import com.ruuddeenen.plannerplus.models.Employee;
import com.ruuddeenen.plannerplus.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = repository.findAll();
        if (employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<>();
        }
    }

    public Employee getEmployeeById(Long id) throws RecordNotFoundException {
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException("No employee record exist for the given id"));
    }

    public void deleteEmployeeById(Long id) throws RecordNotFoundException {
        Optional<Employee> employee = repository.findById(id);

        if (employee.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for the given id");
        }
    }

    public Employee createOrUpdate(Employee entity) {
        if (entity.getId() == null) {
            entity = repository.save(entity);
            return entity;
        } else {
            Employee updated = entity;
            // todo update employee
            return updated;
        }
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
