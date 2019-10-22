package com.autodoc.model.models.tasks;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class TemplateSubTask {

    // Constructors

    public TemplateSubTask() {

    }


    // Parameters


    private int id;


    @NotNull(message = "name cannot be null")
    private String name;

    @Min(value = 1, message = "estimated Time cannot be null")
    private double estimatedTime;

}
