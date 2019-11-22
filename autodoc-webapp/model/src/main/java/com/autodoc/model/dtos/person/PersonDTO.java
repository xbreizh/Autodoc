package com.autodoc.model.dtos.person;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public abstract class PersonDTO {
    private int id;

    // @NotNull(message = "lastName cannot be null")
    private String lastName;
    // @NotNull(message = "firstName cannot be null")
    private String firstName;
    // @NotNull(message = "phoneNumber1 cannot be null")
    private String phoneNumber1;
    private String phoneNumber2;

    public PersonDTO(int id, String lastName, String firstName, String phoneNumber1, String phoneNumber2) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
    }

    public PersonDTO() {
    }
}