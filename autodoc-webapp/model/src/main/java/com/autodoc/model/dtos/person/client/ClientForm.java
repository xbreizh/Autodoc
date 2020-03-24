package com.autodoc.model.dtos.person.client;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ClientForm {


    @NotNull
    @Size(min = 3, max = 12, message = "should be between 3 and 12")
    private String firstName;
    @NotNull
    @Size(min = 3, max = 12, message = "should be between 3 and 12")
    private String lastName;

    private int id;
    @NotNull
    @Size(min = 8, max = 12, message = "should be between 8 and 12")
    private String phoneNumber;


    public ClientForm() {
    }

    public ClientForm(@NotNull @Size(min = 3, max = 12, message = "should be between 3 and 12") String firstName, @NotNull @Size(min = 3, max = 12, message = "should be between 3 and 12") String lastName, @NotNull @Size(min = 8, max = 12, message = "should be between 8 and 12") String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "ClientForm{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}










