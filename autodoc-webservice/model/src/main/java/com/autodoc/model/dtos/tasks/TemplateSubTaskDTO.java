package com.autodoc.model.dtos.tasks;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
@ToString
public class TemplateSubTaskDTO {




    private int id;

    public TemplateSubTaskDTO() {
    }


    @NotNull(message = "name should not be null")
    private String name;
    @NotNull(message = "estimatedTime should not be null")
    private double estimatedTime;

    public TemplateSubTaskDTO(@NotNull String name, @NotNull double estimatedTime) {
        this.name = name;
        this.estimatedTime = estimatedTime;
    }
}
