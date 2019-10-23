package com.autodoc.model.dtos.person.employee;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
public class SkillDTO {

    // Constructors

    public SkillDTO(@NotNull(message = "name cannot be null") String name, @Min(value = 1, message = "skillCategoryId cannot be null") int skillCategoryId) {
        this.name = name;
        this.skillCategoryId = skillCategoryId;
    }


    // Parameters

    private int id;

    @NotNull(message = "name cannot be null")
    private String name;


    @Min(value = 1, message = "skillCategoryId cannot be null")
    private int skillCategoryId;


}
