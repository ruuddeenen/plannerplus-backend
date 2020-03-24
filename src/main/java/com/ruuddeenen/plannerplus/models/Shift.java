package com.ruuddeenen.plannerplus.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
public
class Shift implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private Date day;

    @Column(nullable = false)
    private int startTime;

    @Column(nullable = false)
    private int endTime;


    @OneToMany(mappedBy = "shift", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmployeeShift> employeeShifts;
}
