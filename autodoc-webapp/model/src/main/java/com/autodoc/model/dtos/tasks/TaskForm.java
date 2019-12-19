package com.autodoc.model.dtos.tasks;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class TaskForm {


    private int id;


    @NotNull
    private String name;

    @NotNull
    private String description;


    private int estimatedTime;


    private String template;

    @NotNull
    private double price;


}










