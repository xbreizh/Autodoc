package com.autodoc.model.dtos.person.employee;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class ClientForm {


    private int id;

    @Size(min = 3, max = 12, message = "{firstName.size}")
    private String firstName;


    @Size(min = 3, max = 12, message = "{lastName.size}")
    private String lastName;

    @Size(min = 8, max = 12, message = "{phoneNumber.size}")
    private String phoneNumber1;


}










