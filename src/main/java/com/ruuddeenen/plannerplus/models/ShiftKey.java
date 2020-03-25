package com.ruuddeenen.plannerplus.models;

import java.io.Serializable;
import java.util.Date;

public class ShiftKey implements Serializable {
    private Employee employee;
    private Department department;
    private Date date;

    public ShiftKey() {
    }

    public ShiftKey(Employee employee, Department department, Date date) {
        this.employee = employee;
        this.department = department;
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
