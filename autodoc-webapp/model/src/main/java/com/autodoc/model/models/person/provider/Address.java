package com.autodoc.model.models.person.provider;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class Address {


    private int id;
    @NonNull
    private Provider provider;
    @NonNull
    private Country country;
    @NonNull
    private String streetName;
    private String postcode;
    @NonNull
    private String city;


    public Address(@NonNull Country country, @NonNull String streetName, @NonNull String city) {
        this.country = country;
        this.streetName = streetName;
        this.city = city;
    }

    public Address() {

    }


}
