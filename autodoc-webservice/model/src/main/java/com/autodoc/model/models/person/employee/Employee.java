package com.autodoc.model.models.person.employee;

import com.autodoc.model.enums.Role;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.person.Person;
import com.autodoc.model.models.tasks.SubTask;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee")
@Setter
@Getter
@ToString
public class Employee extends Person {



    public Employee() {
    }


    public Employee(String firstName, String lastName, String phoneNumber1, @NonNull List<Role> roles, @NonNull Date startDate, @NonNull String login, @NonNull String password) {
        super(firstName, lastName, phoneNumber1);
        this.roles = roles;
        this.startDate = startDate;
        this.login = login;
        this.password = password;
    }


    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private List<Bill> bills;

    @NotNull
    @ElementCollection(targetClass = Role.class)
    @Enumerated(EnumType.STRING)
    private List<Role> roles;


    @ManyToMany(mappedBy = "employees", cascade = CascadeType.REMOVE)
    private List<Skill> skills;


    @ManyToMany(mappedBy = "employees", cascade = CascadeType.REMOVE)
    private List<SubTask> subTasks;

    @NotNull
    private Date startDate;

    @NotNull
    private String login;

    @NonNull
    private String password;

    private String token;

    private Date lastConnection;

    private Date tokenExpiration;


}
