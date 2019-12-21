package com.autodoc.model.dtos.tasks;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class TaskDTO {


    private int id;


    private String name;


    private String description;

    private int estimatedTime;


    private String template;

    private double price;

    private List<Integer> pieces;

    public TaskDTO(String name, String description, int estimatedTime, double price, String template) {
        this.name = name;
        this.description = description;
        this.estimatedTime = estimatedTime;
        this.price = price;
        this.template = template;
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
                ", template='" + template + '\'' +
                ", price=" + price +
                ", pieces=" + pieces +
                '}';
    }
}