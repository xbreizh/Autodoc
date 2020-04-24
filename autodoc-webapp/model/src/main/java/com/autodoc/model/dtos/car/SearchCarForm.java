package com.autodoc.model.dtos.car;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class SearchCarForm {

    private int id;

    private int clientId;

    private int modelId;

    private String color;

    private double mileage;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{8,10}$", message = "invalid registration (letters, numbers, between 8 and 10)")
    private String registration;


    @Override
    public String toString() {
        return "SearchCarForm{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", carModelId=" + modelId +
                ", registration='" + registration + '\'' +
                '}';
    }
}