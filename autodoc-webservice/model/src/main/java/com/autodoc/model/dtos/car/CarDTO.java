package com.autodoc.model.dtos.car;


import lombok.*;

import javax.validation.constraints.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class CarDTO {

    private int id;
    @Min(value = 1, message = "carModelId cannot be null")
    @NotNull
    private int carModelId;
    @NotBlank(message = "registration cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9]{8,10}$", message = "invalid registration (letters, numbers, between 8 and 10)")
    private String registration;
    @Min(value = 1, message = "clientId cannot be null")
    private int clientId;
    private String fuelType;
    private double mileage;

    private String color;


}
