package com.autodoc.model.models.person;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Setter
@Getter
@ToString
public abstract class Person /*implements Serializable */{

    // Constructors

    public Person() {
    }

    public Person(String firstName, String lastName, String phoneNumber1) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber1 = phoneNumber1;
    }

    // Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String phoneNumber1;

    private String phoneNumber2;


}
