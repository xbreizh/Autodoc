package com.autodoc.model.dtos.car;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CarModelDTO {


    private int id;
    private int manufacturerId;
    private String name;
    private String description;
    private String gearbox;
    private String engine;
    private String fuelType;


    public CarModelDTO() {
    }

    public CarModelDTO(int id, int manufacturerId, String name, String description, String gearbox, String engine, String fuelType) {
        this.id = id;
        this.manufacturerId = manufacturerId;
        this.name = name;
        this.description = description;
        this.gearbox = gearbox;
        this.engine = engine;
        this.fuelType = fuelType;
    }
}
