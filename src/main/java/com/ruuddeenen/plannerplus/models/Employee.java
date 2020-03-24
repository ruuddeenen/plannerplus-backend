package com.ruuddeenen.plannerplus.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
public class Employee implements Serializable {

    @Id
    @JsonProperty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty
    @Column(nullable = false)
    private String name;

    @JsonProperty
    @Column(nullable = false)
    private String surname;

    @ManyToMany
    @JoinColumn
    private List<Department> departments;

    @OneToMany(mappedBy = "employee")
    private Set<EmployeeShift> employeeShifts;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
