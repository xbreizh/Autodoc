package com.autodoc.model.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class RegistrationForm {

    @NotNull
    @Size(min = 5, max = 12, message = "{registration.size}")
    private String registration;


   /* public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return "RegistrationForm{" +
                "registration='" + registration + '\'' +
                '}';
    }*/
}