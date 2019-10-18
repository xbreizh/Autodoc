package com.autodoc.model.dtos.person;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public abstract class PersonDTO {

    public PersonDTO(@NonNull int id, @NonNull String lastName, @NonNull String firstName, @NonNull String phoneNumber1) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber1 = phoneNumber1;
    }

    @NonNull
    private int id;
    // Parameters
    @NonNull
    private String lastName;
    @NonNull
    private String firstName;
    @NonNull
    private String phoneNumber1;
    private String phoneNumber2;


}