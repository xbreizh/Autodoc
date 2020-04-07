package com.autodoc.model.dtos.person.provider;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class CountryDTO {


    private int id;


    @NotNull(message = "name cannot be null")
    @Size(min = 2, max = 30, message = "name should have between {min} and {max} characters")
    private String name;


}
