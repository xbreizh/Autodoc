package com.autodoc.model.dtos.tasks;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;


@Getter
@Setter
public class TaskDTO {


    private int id;


    private String name;


    private String description;

    @DecimalMin(value = "0.5", message = "estimatedTime cannot be null")
    private double estimatedTime;


    //  private String template;

    public TaskDTO(String name, String description, double estimatedTime/*, String template*/) {
        this.name = name;
        this.description = description;
        this.estimatedTime = estimatedTime;
        // this.template = template;
    }

    public TaskDTO() {
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public void setDescription(String description) {
        this.description = description.toUpperCase();
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", estimatedTime=" + estimatedTime +
                // ", template='" + template + '\'' +
                '}';
    }
}