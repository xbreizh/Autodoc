package com.autodoc.model.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Getter
@Setter
public class ManufacturerDTO {

    private int identifier;

   /* @NotNull(message = "name cannot be null")
    @Size(min = 2, max = 50, message = "name should have between 2 and 50 characters")*/
    private String name;


    public ManufacturerDTO(int identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }


    @Override
    public String toString() {
        return "ManufacturerDTO{" +
                "identifier=" + identifier +
                ", name='" + name + '\'' +
                '}';
    }
}
