package com.ruuddeenen.plannerplus.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
class EmployeeShift implements Serializable {
    @Id
    @ManyToOne
    private Shift shift;

    @Id
    @ManyToOne
    private Employee employee;

    @Column(name = "departmentId")
    private Department department;
}
