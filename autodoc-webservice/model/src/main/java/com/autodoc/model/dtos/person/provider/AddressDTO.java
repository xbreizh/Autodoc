package com.autodoc.model.dtos.person.provider;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Generated
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


}
