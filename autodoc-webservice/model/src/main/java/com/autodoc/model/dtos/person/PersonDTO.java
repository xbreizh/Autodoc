package com.autodoc.model.dtos.person;

import lombok.*;

import javax.validation.constraints.NotNull;


@Data
public abstract class PersonDTO {

    public PersonDTO(int id, @NotNull(message = "lastName cannot be null") String lastName, @NotNull(message = "firstName cannot be null") String firstName, @NotNull(message = "phoneNumber1 cannot be null") String phoneNumber1, String phoneNumber2) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
    }

    private int id;
    // Parameters
    @NotNull(message = "lastName cannot be null")
    private String lastName;
    @NotNull(message = "firstName cannot be null")
    private String firstName;
    @NotNull(message = "phoneNumber1 cannot be null")
    private String phoneNumber1;
    private String phoneNumber2;


}