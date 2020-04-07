package com.autodoc.model.dtos.car;


import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
    @NotNull(message = "registration cannot be null")
    private String registration;
    @Min(value = 1, message = "clientId cannot be null")
    private int clientId;


}
