package com.autodoc.model.models.person.employee;

import com.autodoc.model.models.person.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Employee extends Person {

    private String role;

    private Date startDate;

    private String login;

    private Date lastConnection;


    public Employee(int id, String firstName, String lastName, String phoneNumber, String role, Date startDate, String login, Date lastConnection) {
        super(id, firstName, lastName, phoneNumber);
        this.role = role;
        this.startDate = startDate;
        this.login = login;
        this.lastConnection = lastConnection;
    }

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "role=" + role +
                ", startDate=" + startDate +
                ", login='" + login + '\'' +
                ", lastConnection=" + lastConnection +
                "} " + super.toString();
    }
}
