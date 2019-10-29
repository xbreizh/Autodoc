package com.autodoc.model.models.person.provider;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Setter
@Getter
@ToString
public class Address {



     public Address(@NonNull Country country, @NonNull String streetName, @NonNull String city) {
        this.country = country;
        this.streetName = streetName;
        this.city = city;
    }


    public Address() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
