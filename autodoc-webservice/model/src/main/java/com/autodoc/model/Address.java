package com.autodoc.model;

import com.autodoc.model.person.Provider;
import lombok.Getter;
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

    public Address(Country country, String streetName, String city) {
        this.country = country;
        this.streetName = streetName;
        this.city = city;
    }

    // Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    private Provider provider;

    @ManyToOne
    private Country country;

    private String streetName;

    private String postcode;


    private String city;
}
