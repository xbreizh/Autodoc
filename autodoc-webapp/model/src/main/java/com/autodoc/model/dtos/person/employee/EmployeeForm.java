package com.autodoc.model.dtos.person.employee;

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


    @Size(min = 3, max = 12, message = "{firstName.size}")
    private String firstName;


    @Size(min = 3, max = 12, message = "{lastName.size}")
    private String lastName;

    @Size(min = 8, max = 12, message = "{phoneNumber.size}")
    private String phoneNumber1;

    @Size(min = 1, max = 3, message = "{roles.size}")
    @NotNull( message = "{roles.size}")
    private List<String> roles;

   // @Size(min = 3, max = 8, message = "{password.size}")
    private String password;

}










