package com.autodoc.model.models.person.provider;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address")
@Setter @Getter @ToString
public class Address implements Serializable {

    // Constructors

    public Address() {
    }



    // Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @NonNull
    private Provider provider;

    @ManyToOne
    @NonNull
    private Country country;

    @NonNull
    private String streetName;


    private String postcode;

    @NonNull
    private String city;
}
