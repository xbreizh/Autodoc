package com.autodoc.model.models.tasks;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Task {


    private int id;


    @NotNull
    private String name;


    private String description;

    @NotNull
    private double estimatedTime;


    @NotNull
    private boolean template;


    public Task(String name, String description, double estimatedTime, boolean template) {
        this.name = name;
        this.description = description;
        this.estimatedTime = estimatedTime;
        this.template = template;
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
                ", template=" + template +
                '}';
    }
}
