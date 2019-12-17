package com.autodoc.model.dtos.person.provider;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class ProviderForm {


    private int id;

    @Size(min = 3, max = 12, message = "{website.size}")
    private String website;

    @Size(min = 3, max = 12, message = "{company.size}")
    private String company;


    @Size(min = 3, max = 12, message = "{firstName.size}")
    private String firstName;

    @Size(min = 3, max = 12, message = "{email.size}")
    private String email1;


    @Size(min = 3, max = 12, message = "{lastName.size}")
    private String lastName;

    @Size(min = 8, max = 12, message = "{phoneNumber.size}")
    private String phoneNumber1;


}










