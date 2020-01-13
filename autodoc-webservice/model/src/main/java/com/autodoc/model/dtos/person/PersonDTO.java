package com.autodoc.model.dtos.person;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class PersonDTO {
    private int id;

    @NotNull(message = "lastName cannot be null")
    @Size(min = 3, max = 20, message = "the size for lastName should be between {min} and {max}")
    private String lastName;
    @Size(min = 3, max = 20, message = "the size for firstName should be between {min} and {max}")
    @NotNull(message = "firstName cannot be null")
    private String firstName;
    @Size(min = 8, max = 12, message = "the size for phoneNumber should be between {min} and {max}")
    @NotNull(message = "phoneNumber cannot be null")
    private String phoneNumber;

    public PersonDTO(@NotNull(message = "lastName cannot be null") String lastName, @NotNull(message = "firstName cannot be null") String firstName, @NotNull(message = "phoneNumber cannot be null") String phoneNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
    }

    public PersonDTO() {
    }
}