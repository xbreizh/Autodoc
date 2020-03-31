package com.autodoc.model.dtos.person.provider;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO {


    private int id;


    @NotNull(message = "name cannot be null")
    @Size(min = 2, max = 30, message = "name should have between {min} and {max} characters")
    private String name;


}
