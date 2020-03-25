package com.ruuddeenen.plannerplus.services;

import com.ruuddeenen.plannerplus.exceptions.RecordNotFoundException;
import com.ruuddeenen.plannerplus.models.Department;
import com.ruuddeenen.plannerplus.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    public List<Department> getAllDepartments() {
        List<Department> departmentList = repository.findAll();
        if (departmentList.size() > 0) {
            return departmentList;
        } else return new ArrayList<>();
    }

    public Department getDepartmentById(Long id) throws RecordNotFoundException {
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException("No department record exist for the given id"));
    }

    public Department createOrUpdate(Department department) {
        if (department.getId() == null) {
            department = repository.save(department);
            return department;
        } else {
            Optional<Department> optional = repository.findById(department.getId());
            if (optional.isPresent()) {
                Department updated = optional.get();
                updated.setName(department.getName());
                updated = repository.save(updated);
                return updated;
            }
            return null;
        }
    }

    public void deleteDepartmentById(Long id) throws RecordNotFoundException {
        Optional<Department> department = repository.findById(id);
        if (department.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No department record exist for the given id");
        }
    }
}