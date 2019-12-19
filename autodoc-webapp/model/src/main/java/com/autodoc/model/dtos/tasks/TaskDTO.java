package com.autodoc.model.dtos.tasks;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TaskDTO {


    private int id;


    private String name;


    private String description;

    private int estimatedTime;


    private String template;

    private double price;

    public TaskDTO(String name, String description, int estimatedTime, double price, String template) {
        this.name = name;
        this.description = description;
        this.estimatedTime = estimatedTime;
        this.price = price;
        this.template = template;
    }

    public TaskDTO() {
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
                '}';
    }
}