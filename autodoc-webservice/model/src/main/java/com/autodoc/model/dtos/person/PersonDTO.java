package com.autodoc.model.dtos.person;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class PersonDTO {
    private int id;
    // Parameters
    @NotNull(message = "lastName cannot be null")
    private String lastName;
    @NotNull(message = "firstName cannot be null")
    private String firstName;
    @NotNull(message = "phoneNumber1 cannot be null")
    private String phoneNumber1;
    private String phoneNumber2;
    public PersonDTO(@NotNull(message = "lastName cannot be null") String lastName, @NotNull(message = "firstName cannot be null") String firstName, @NotNull(message = "phoneNumber1 cannot be null") String phoneNumber1) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber1 = phoneNumber1;
    }


}