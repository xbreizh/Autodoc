package com.autodoc.model.models.person.employee;

import com.autodoc.model.models.person.Person;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Employee extends Person {

    private List<String> roles;

    // @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private String startDate;

    private String login;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date lastConnection;

    private String password;


    public Employee(int id, String firstName, String lastName, String phoneNumber, List<String> roles, String startDate, String login, Date lastConnection) {
        super(id, firstName, lastName, phoneNumber);
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
