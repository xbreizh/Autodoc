package com.autodoc.model.dtos.car;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ManufacturerDTO {

    private int identifier;


    private String name;


    public ManufacturerDTO(int identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }

    public ManufacturerDTO() {
    }
}
