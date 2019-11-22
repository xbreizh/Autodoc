package com.autodoc.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDTO {

    private int id;

    private int modelId;

    private String registration;

    private int clientId;


    public CarDTO() {
    }


    public CarDTO(int id, int modelId, String registration, int clientId) {
        this.id = id;
        this.modelId = modelId;
        this.registration = registration;
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "CarDTO{" +
                "id=" + id +
                ", modelId=" + modelId +
                ", registration='" + registration + '\'' +
                ", clientId=" + clientId +
                '}';
    }
}
