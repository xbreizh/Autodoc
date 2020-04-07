package com.autodoc.model.dtos.car;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Generated
public class ManufacturerDTO {

    private int id;

    @NotNull(message = "name cannot be null")
    @Size(min = 2, max = 50, message = "name should have between 2 and 50 characters")
    private String name;


}
