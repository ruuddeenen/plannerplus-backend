package com.ruuddeenen.plannerplus.services;

import com.ruuddeenen.plannerplus.models.Department;
import com.ruuddeenen.plannerplus.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    public List<Department> list() {
        return repository.findAll();
    }
}
