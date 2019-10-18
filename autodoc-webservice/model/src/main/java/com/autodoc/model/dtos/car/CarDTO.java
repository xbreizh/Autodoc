package com.autodoc.model.dtos.car;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CarDTO {


    // Parameters
    @NonNull
    private int id;
    @NonNull
    private int carModelId;
    @NonNull
    private String registration;

    @NonNull
    private int clientId;


    // Constructors
    public CarDTO(String registration, int carModelId, int clientId) {
        this.registration = registration;
        this.carModelId = carModelId;
        this.clientId = clientId;
    }


}
