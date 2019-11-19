package com.autodoc.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Car {

    private int id;

    //@Min(value = 1, message = "carModelId cannot be null")
    private int carModelId;

    //@NotNull(message = "registration cannot be null")
    private String registration;

    //@Min(value = 1, message = "clientId cannot be null")
    private int clientId;


    public Car() {
    }


    public Car(int id, int carModelId, String registration, int clientId) {
        this.id = id;
        this.carModelId = carModelId;
        this.registration = registration;
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carModelId=" + carModelId +
                ", registration='" + registration + '\'' +
                ", clientId=" + clientId +
                '}';
    }
}
