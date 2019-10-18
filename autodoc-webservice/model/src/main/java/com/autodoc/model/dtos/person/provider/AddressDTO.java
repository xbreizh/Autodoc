package com.autodoc.model.dtos.person.provider;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AddressDTO {

    // Constructors

    @NonNull
    private int id;


    // Parameters
    @NonNull
    private ProviderDTO provider;
    @NonNull
    private CountryDTO country;
    @NonNull
    private String streetName;
    private String postcode;
    @NonNull
    private String city;

    public AddressDTO(@NonNull CountryDTO country, @NonNull String streetName, @NonNull String city) {
        this.country = country;
        this.streetName = streetName;
        this.city = city;
    }
}
