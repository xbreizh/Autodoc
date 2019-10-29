package com.autodoc.model.dtos.person.employee;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class SkillCategoryDTO {


    // Constructor

    public SkillCategoryDTO(String name) {
        this.name = name;
    }

    public SkillCategoryDTO() {
    }


    private int id;


    @NotNull(message = "name cannot be null")
    private String name;


}
