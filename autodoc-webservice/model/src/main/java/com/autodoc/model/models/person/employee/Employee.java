package com.autodoc.model.models.person.employee;

import com.autodoc.model.models.Bill;
import com.autodoc.model.models.person.Person;
import com.autodoc.model.models.tasks.SubTask;
import com.autodoc.model.enums.Role;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee")
@Setter @Getter @ToString
public class Employee extends Person {

    // Constructors

    public Employee() {
    }



    // Parameters
    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private List<Bill> bills;

    @NonNull
    @ElementCollection(targetClass = Role.class)
    @Enumerated(EnumType.STRING)
    private List<Role> roles;


    @ManyToMany(mappedBy = "employees", cascade = CascadeType.REMOVE)
    private List<Skill> skills;


    @ManyToMany(mappedBy = "employees", cascade = CascadeType.REMOVE)
    private List<SubTask> subTasks;

    @NonNull
    private Date startDate;

    @NonNull
    private String login;

    @NonNull
    private String password;




}
