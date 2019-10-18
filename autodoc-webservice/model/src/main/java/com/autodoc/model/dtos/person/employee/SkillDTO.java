package com.autodoc.model.dtos.person.employee;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class SkillDTO {

    // Constructors

    public SkillDTO(@NonNull int id, @NonNull String name, @NonNull int skillCategoryId) {
        this.id = id;
        this.name = name;
        this.skillCategoryId = skillCategoryId;
    }


    // Parameters

    @NonNull
    private int id;

    @NonNull
    private String name;


    @NonNull
    private int skillCategoryId;


}
