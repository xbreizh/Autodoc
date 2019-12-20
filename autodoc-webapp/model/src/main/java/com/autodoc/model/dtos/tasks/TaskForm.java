package com.autodoc.model.dtos.tasks;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TaskForm {


    private int id;


    @NotEmpty(message = "name cannot be empty")
    private String name;

    @NotEmpty(message = "description cannot be empty")
    private String description;

    @Min(value = 1, message = "estimatedTime cannot be null")
    private int estimatedTime;

    @NotEmpty(message = "template must be true or false")
    private String template;

    @Min(value = 1, message = "price cannot be null")
    private double price;

    public TaskForm() {
    }


    @Override
    public String toString() {
        return "TaskForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", estimatedTime=" + estimatedTime +
                ", template='" + template + '\'' +
                ", price=" + price +
                '}';
    }
}










