package com.autodoc.model.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PersonDTO {

    //@NotNull(message = "lastName cannot be null")
    private String lastName;
   // @NotNull(message = "firstName cannot be null")
    private String firstName;
    //@NotNull(message = "phoneNumber1 cannot be null")
    private String phoneNumber1;
    private String phoneNumber2;

    public PersonDTO(String lastName, String firstName, String phoneNumber1, String phoneNumber2) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
    }


    public PersonDTO() {
    }


    @Override
    public String toString() {
        return "PersonDTO{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", phoneNumber1='" + phoneNumber1 + '\'' +
                ", phoneNumber2='" + phoneNumber2 + '\'' +
                '}';
    }
}
