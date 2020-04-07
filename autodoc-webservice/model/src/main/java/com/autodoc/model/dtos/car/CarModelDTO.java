package com.autodoc.model.dtos.car;

import com.autodoc.model.enums.FuelType;
import com.autodoc.model.enums.GearBox;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class CarModelDTO {


    private int id;


    @Min(value = 1, message = "manufacturerId cannot be null")
    private int manufacturerId;

    @NotNull(message = "name cannot be null")
    private String name;

    @NotNull(message = "registration cannot be null")
    private String description;

    @NotNull(message = "gearbox cannot be null")
    @Enumerated(EnumType.STRING)
    private GearBox gearbox;

    @NotNull(message = "engine cannot be null")
    private String engine;

    @NotNull(message = "fuelType cannot be null")
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;


}
