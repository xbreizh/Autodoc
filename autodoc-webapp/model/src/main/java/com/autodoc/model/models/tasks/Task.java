package com.autodoc.model.models.tasks;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Task {


    private int id;


    @NotNull
    private String name;


    private String description;

    @DecimalMin(value = "0.5", message = "estimatedTime cannot be null")
    private double estimatedTime;


    @NotNull
    private boolean template;


    public Task(String name, String description, double estimatedTime) {
        this.name = name;
        this.description = description;
        this.estimatedTime = estimatedTime;
    }

    public Task() {
    }


    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public void setDescription(String description) {
        this.description = description.toUpperCase();
    }


    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", estimatedTime=" + estimatedTime +
                '}';
    }


}
