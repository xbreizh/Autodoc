package com.autodoc.model.person;

import com.autodoc.model.Bill;
import com.autodoc.model.SubTask;
import com.autodoc.model.enums.Role;
import lombok.Getter;
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


    public Employee(String firstName, String lastName, String phoneNumber1, List<Role> roles, Date startDate, String login, String password) {
        super(firstName, lastName, phoneNumber1);
        this.roles = roles;
        this.startDate = startDate;
        this.login = login;
        this.password = password;
    }

    // Parameters
    @OneToMany(mappedBy = "employee")
    private List<Bill> bills;
    @ElementCollection(targetClass = Role.class)
    @Enumerated(EnumType.STRING)
    private List<Role> roles;
    @ManyToMany
    private List<Skill> skills;


    @ManyToMany
    private List<SubTask> subTasks;
    private Date startDate;
    private String login;
    private String password;




}
