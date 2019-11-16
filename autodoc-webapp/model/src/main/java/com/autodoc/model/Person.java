package com.autodoc.model;

import javax.validation.constraints.NotNull;

public abstract class Person {
    private int id;

    @NotNull(message = "lastName cannot be null")
    private String lastName;
    @NotNull(message = "firstName cannot be null")
    private String firstName;
    @NotNull(message = "phoneNumber1 cannot be null")
    private String phoneNumber1;
    private String phoneNumber2;

    public Person(@NotNull(message = "lastName cannot be null") String lastName, @NotNull(message = "firstName cannot be null") String firstName, @NotNull(message = "phoneNumber1 cannot be null") String phoneNumber1) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber1 = phoneNumber1;
    }

    public Person() {
    }
}