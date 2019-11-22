package com.autodoc.model.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CarModelDTO {

 //   @Min(value = 1, message = "manufacturerId cannot be null")
    private int manufacturerId;

   // @NotNull(message = "name cannot be null")
    private String name;

   // @NotNull(message = "registration cannot be null")
    private String description;

 //   @NotNull(message = "gearbox cannot be null")
  //  @Enumerated(EnumType.STRING)
    private String gearbox;

  //  @NotNull(message = "engine cannot be null")
    private String engine;

  //  @NotNull(message = "fuelType cannot be null")
  //  @Enumerated(EnumType.STRING)
    private String fuelType;


    public CarModelDTO(int manufacturerId, String name, String description, String gearbox, String engine, String fuelType) {
        this.manufacturerId = manufacturerId;
        this.name = name;
        this.description = description;
        this.gearbox = gearbox;
        this.engine = engine;
        this.fuelType = fuelType;
    }


    @Override
    public String toString() {
        return "CarModelDTO{" +
                "manufacturerId=" + manufacturerId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", gearbox='" + gearbox + '\'' +
                ", engine='" + engine + '\'' +
                ", fuelType='" + fuelType + '\'' +
                '}';
    }
}
