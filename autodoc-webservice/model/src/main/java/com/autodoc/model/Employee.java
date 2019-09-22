package com.autodoc.model;

import com.autodoc.model.enums.Role;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee extends Person {

    private Date startDate;

    private Role role;


    @OneToMany(mappedBy = "employee")
    private List<Bill> bills;
}
