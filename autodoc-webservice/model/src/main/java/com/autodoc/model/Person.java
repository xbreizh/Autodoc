package com.autodoc.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class Person {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "firstName")
    private String firstName;

    private String lastName;



}
