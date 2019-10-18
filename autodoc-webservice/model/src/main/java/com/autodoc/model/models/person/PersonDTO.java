package com.autodoc.model.models.person;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public abstract class PersonDTO {

    // Constructors

    private int id;
    @NonNull
    private String firstName;

    // Parameters
    @NonNull
    private String lastName;
    @NonNull
    private String phoneNumber1;
    private String phoneNumber2;

    public PersonDTO() {
    }

    public PersonDTO(String firstName, String lastName, String phoneNumber1) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber1 = phoneNumber1;
    }
}