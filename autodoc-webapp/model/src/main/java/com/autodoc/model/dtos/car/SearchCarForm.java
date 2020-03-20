package com.autodoc.model.dtos.car;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SearchCarForm {

    private int id;

    private int clientId;

    private int modelId;

    @NotNull
    @Size(min = 5, max = 12, message = "{registration.size}")
    private String registration;

    public SearchCarForm() {
    }

    @Override
    public String toString() {
        return "CarForm{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", carModelId=" + modelId +
                ", registration='" + registration + '\'' +
                '}';
    }
}