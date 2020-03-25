package com.ruuddeenen.plannerplus.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Entity
@IdClass(ShiftKey.class)
public class Shift implements Serializable {
    @Id
    private Employee employee;

    @Id
    private Department department;

    @Id
    private Date date;

    private Time startTime;

    private Time endTime;

    public Shift() {
    }
}
