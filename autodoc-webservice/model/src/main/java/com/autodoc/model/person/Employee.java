package com.autodoc.model.person;

import com.autodoc.model.Bill;
import com.autodoc.model.SubTask;
import com.autodoc.model.enums.Role;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
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

    @ManyToMany
    private List<SubTask> subTasks;
}
