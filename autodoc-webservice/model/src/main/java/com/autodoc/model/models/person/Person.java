package com.autodoc.model.models.person;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Setter
@Getter
public abstract class Person {





    public Person() {
    }

    public Person(String firstName, String lastName, String phoneNumber1) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber1 = phoneNumber1;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @NonNull
    protected String firstName;

    @NonNull
    protected String lastName;

    @NonNull
    protected String phoneNumber1;



    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber1='" + phoneNumber1 + '\'' +
                '}';
    }
}
