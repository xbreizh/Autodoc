package com.autodoc.model.models.car;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

@Setter
@Getter
@ToString
public class CarDTO /*implements Serializable */ {

    // Constructors

    private int id;
    @NonNull
    @Column(name = "registration", unique = true)
    private String registration;

    // Parameters
    @NonNull
    private int carModelId;
    @NonNull
    private int clientId;

    public CarDTO() {
    }

    public CarDTO(String registration, int carModelId, int clientId) {
        this.registration = registration;
        this.carModelId = carModelId;
        this.clientId = clientId;
    }


}
