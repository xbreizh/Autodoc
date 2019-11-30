package com.autodoc.model.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonForm {

    @NotNull
    @Size(min = 2, max = 30)
    private String registration;


    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return "PersonForm{" +
                "registration='" + registration + '\'' +
                '}';
    }
}