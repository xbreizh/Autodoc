package com.autodoc.model.person;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Setter @Getter @ToString
public abstract class Person implements Serializable {

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private String phoneNumber1;

    private String phoneNumber2;


}
