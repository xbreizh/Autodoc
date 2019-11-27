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

    public PersonDTO(int id, String firstName, String lastName, String phoneNumber1) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber1 = phoneNumber1;
    }

    public PersonDTO() {
    }
}