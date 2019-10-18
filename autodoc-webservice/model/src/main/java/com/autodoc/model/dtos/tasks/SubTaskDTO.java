package com.autodoc.model.dtos.tasks;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class SubTaskDTO {

    // Constructors

    @NonNull
    private int id;


    // Parameters
    @NonNull
    private int templateSubTaskId;
    @NonNull
    private String name;
    @NonNull
    private double estimatedTime;

    public SubTaskDTO(@NonNull int id, @NonNull int templateSubTaskId, @NonNull String name, @NonNull double estimatedTime) {
        this.id = id;
        this.templateSubTaskId = templateSubTaskId;
        this.name = name;
        this.estimatedTime = estimatedTime;
    }


}
