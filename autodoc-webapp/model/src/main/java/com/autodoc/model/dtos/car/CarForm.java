package com.autodoc.model.dtos.car;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CarForm {


    @NotNull
    @Size(min = 5, max = 12, message = "{registration.size}")
    private String registration;

    private int modelId;

    /* @Valid
     @NotNull
     private ClientForm clientForm;
 */
    @NotNull
    @Size(min = 3, max = 12, message = "should be between 3 and 12")
    private String firstName;


    @NotNull
    @Size(min = 3, max = 12, message = "should be between 3 and 12")
    private String lastName;

    @NotNull
    @Size(min = 8, max = 12, message = "should be between 8 and 12")
    private String phoneNumber;

    public CarForm() {
    }


    @Override
    public String toString() {
        return "CarForm{" +
                "registration='" + registration + '\'' +
                ", modelId=" + modelId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}