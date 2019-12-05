package com.autodoc.model.dtos.person.provider;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AddressDTO {


    private int id;


    @NotNull
    @Size(min = 2, max = 30, message = "countryName should have between {min} and {max} characters")
    private String countryName;

    @NotNull(message = "streetName cannot be null")
    @Size(min = 2, max = 30, message = "streetName mush have between {min} and {max} characters")
    private String streetName;

    @Size(min = 2, max = 10, message = "postcode mush have between {min} and {max} characters")
    private String postcode;

    @NotNull(message = "city cannot be null")
    @Size(min = 2, max = 30, message = "city mush have between {min} and {max} characters")
    private String city;

    public AddressDTO(int id, @Min(value = 1, message = "countryName cannot be null") String countryName, @NotNull(message = "streetName cannot be null") String streetName, String postcode, @NotNull(message = "city cannot be null") String city) {
        this.id = id;
        this.countryName = countryName;
        this.streetName = streetName;
        this.postcode = postcode;
        this.city = city;
    }

    public AddressDTO() {
    }
}
