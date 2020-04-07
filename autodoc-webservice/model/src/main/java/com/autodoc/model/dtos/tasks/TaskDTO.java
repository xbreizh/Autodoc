package com.autodoc.model.dtos.tasks;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Positive;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class TaskDTO {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String name;
    private String description;
    @Positive(message = "there should be an estimated time")
    private double estimatedTime;


    @Override
    public String toString() {
        return "TaskDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", estimatedTime=" + estimatedTime +
                '}';
    }
}

