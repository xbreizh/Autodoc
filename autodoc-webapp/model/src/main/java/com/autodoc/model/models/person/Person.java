package com.autodoc.model.models.person;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Person {
    private int id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    public Person(int id, String firstName, String lastName, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }


    public Person() {
    }


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}