package com.autodoc.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Employee /*extends Person*/{

    //@NotNull(message = "role should not be null")
    private List<String> roles;

   // @PastOrPresent
    private Date startDate;

    //@NotNull(message = "login cannot be null")
    private String login;

   // @PastOrPresent
    private Date lastConnection;

    private String firstName;

    private String lastName;

    private String phoneNumber1;

    public Employee(List<String> roles, Date startDate, String login, Date lastConnection, String firstName, String lastName, String phoneNumber1) {
        this.roles = roles;
        this.startDate = startDate;
        this.login = login;
        this.lastConnection = lastConnection;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber1 = phoneNumber1;
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
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber1='" + phoneNumber1 + '\'' +
                '}';
    }
}
