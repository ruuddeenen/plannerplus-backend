package com.ruuddeenen.plannerplus.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public
class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}