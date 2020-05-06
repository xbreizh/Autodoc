package com.autodoc.model.dtos.person.employee;

import com.autodoc.model.ContactNumberConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@ToString
public class EmployeeForm {


    private int id;

    @Size(min = 3, max = 12, message = "{login.size}")
    private String login;

    // ((0[1-9]|[12]\d|3[01])-(0[1-9]|1[0-2])-[12]\d{3})
    private String startDate;


    @Size(min = 3, max = 12, message = "{firstName.size}")
    private String firstName;


    @Size(min = 3, max = 12, message = "{lastName.size}")
    private String lastName;

    @ContactNumberConstraint(message = "invalid phoneNumber")
    private String phoneNumber;

    @Size(min = 1, max = 3, message = "{roles.size}")
    @NotNull(message = "{roles.size}")
    private List<String> roles;

    // @Size(min = 3, max = 8, message = "{password.size}")
    private String password;

}










