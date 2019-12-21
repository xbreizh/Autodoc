package com.autodoc.model.dtos.car;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDTO {

    private int id;

    private int carModelId;

    private String registration;

    private int clientId;


    public CarDTO() {
    }


    public CarDTO(int id, int carModelId, String registration, int clientId) {
        this.id = id;
        this.carModelId = carModelId;
        this.registration = registration;
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "CarDTO{" +
                "id=" + id +
                ", carModelId=" + carModelId +
                ", registration='" + registration + '\'' +
                ", clientId=" + clientId +
                '}';
    }

    public void setRegistration(String registration) {
        this.registration = registration.toUpperCase();
    }
}
