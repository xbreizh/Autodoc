package com.autodoc.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class CarModel {

    private int id;

    private Manufacturer manufacturer;

    private String name;

    private String description;

    private String gearbox;

    private String engine;

    private String fuelType;


    public CarModel(int id, Manufacturer manufacturer, String name, String description, String gearbox, String engine, String fuelType) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.name = name;
        this.description = description;
        this.gearbox = gearbox;
        this.engine = engine;
        this.fuelType = fuelType;
    }


    public CarModel() {
    }


    @Override
    public String toString() {
        return "CarModel{" +
                "id=" + id +
                ", manufacturer=" + manufacturer.getName() +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", gearbox='" + gearbox + '\'' +
                ", engine='" + engine + '\'' +
                ", fuelType='" + fuelType + '\'' +
                '}';
    }
}
