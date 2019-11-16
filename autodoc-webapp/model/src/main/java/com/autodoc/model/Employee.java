package com.autodoc.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Employee extends Person{

    @NotNull(message = "role should not be null")
    private List<String> roles;

    @PastOrPresent
    private Date startDate;

    @NotNull(message = "login cannot be null")
    private String login;

    @PastOrPresent
    private Date lastConnection;

    public Employee(@NotNull(message = "lastName cannot be null") String lastName, @NotNull(message = "firstName cannot be null") String firstName, @NotNull(message = "phoneNumber1 cannot be null") String phoneNumber1, @NotNull(message = "role should not be null") List<String> roles, @PastOrPresent Date startDate, @NotNull(message = "login cannot be null") String login) {
        super(lastName, firstName, phoneNumber1);
        this.roles = roles;
        this.startDate = startDate;
        this.login = login;
        this.roles = roles;
    }

    public Employee() {
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "roles=" + roles +
                ", startDate=" + startDate +
                ", login='" + login + '\'' +
                ", lastConnection=" + lastConnection +
                '}';

    }
}
