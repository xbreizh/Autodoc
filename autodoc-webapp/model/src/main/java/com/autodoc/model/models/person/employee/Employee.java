package com.autodoc.model.models.person.employee;

import com.autodoc.model.models.person.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Employee extends Person {

    private List<String> roles;

    private Date startDate;

    private String login;

    private Date lastConnection;


    public Employee(int id, String firstName, String lastName, String phoneNumber1, List<String> roles, Date startDate, String login, Date lastConnection) {
        super(id, firstName, lastName, phoneNumber1);
        this.roles = roles;
        this.startDate = startDate;
        this.login = login;
        this.lastConnection = lastConnection;
    }

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "roles=" + roles +
                ", startDate=" + startDate +
                ", login='" + login + '\'' +
                ", lastConnection=" + lastConnection +
                "} " + super.toString();
    }
}
