package com.autodoc.model.dtos.person.provider;

import com.autodoc.model.ContactNumberConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
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

    @Email(message = "{email.size}")
    private String email;


    @Size(min = 3, max = 12, message = "{lastName.size}")
    private String lastName;

    @ContactNumberConstraint(message = "invalid phoneNumber")
    private String phoneNumber;


}










