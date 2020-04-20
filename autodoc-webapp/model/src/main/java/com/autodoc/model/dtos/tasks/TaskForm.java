package com.autodoc.model.dtos.tasks;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class TaskForm {


    private int id;


    @NotEmpty(message = "name cannot be empty")
    @Size(min = 2, max = 50, message = "{task.name.size}")
    private String name;

    @NotEmpty(message = "description cannot be empty")
    private String description;

    @DecimalMin(value = "0.5", message = "estimatedTime cannot be null")
    private double estimatedTime;

   /* @NotEmpty(message = "template must be true or false")
    private boolean template;*/


    public TaskForm() {
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }


    public void setDescription(String description) {
        this.description = description.toUpperCase();
    }

    @Override
    public String toString() {
        return "TaskForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", estimatedTime=" + estimatedTime +
                //   ", template='" + template + '\'' +
                '}';
    }
}










