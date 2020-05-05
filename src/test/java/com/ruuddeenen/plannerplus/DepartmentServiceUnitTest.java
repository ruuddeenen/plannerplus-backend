package com.ruuddeenen.plannerplus;

import com.ruuddeenen.plannerplus.web.models.Department;
import com.ruuddeenen.plannerplus.web.services.DepartmentService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DepartmentServiceUnitTest {
    @Autowired
    private DepartmentService departmentService;

    @Test
    public void whenApplicationStarts_thenHibernateCreatesInitialRecords(){
        List<Department> departmentList = departmentService.getAllDepartments();

        Assert.assertEquals(5, departmentList.size());
    }
}
