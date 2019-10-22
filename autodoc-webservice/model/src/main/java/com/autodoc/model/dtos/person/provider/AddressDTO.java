package com.autodoc.model.dtos.person.provider;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class AddressDTO {

    // Constructors


    private int id;


    // Parameters
    @Min(value = 1, message = "providerId cannot be null")
    private int providerId;

    @Min(value = 1, message = "countryId cannot be null")
    private int countryId;

    @NotNull(message = "streetName cannot be null")
    private String streetName;

    private String postcode;

    @NotNull(message = "city cannot be null")
    private String city;

    public AddressDTO(int id, @Min(value = 1, message = "providerId cannot be null") int providerId, @Min(value = 1, message = "countryId cannot be null") int countryId, @NotNull(message = "streetName cannot be null") String streetName, String postcode, @NotNull(message = "city cannot be null") String city) {
        this.id = id;
        this.providerId = providerId;
        this.countryId = countryId;
        this.streetName = streetName;
        this.postcode = postcode;
        this.city = city;
    }
}
