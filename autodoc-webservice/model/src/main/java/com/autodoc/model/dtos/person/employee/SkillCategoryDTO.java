package com.autodoc.model.dtos.person.employee;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class SkillCategoryDTO {


    // Constructor

    public SkillCategoryDTO(String name) {
        this.name = name;
    }


    // Parameters
    @NonNull
    private int id;


    @NonNull
    private String name;
}
