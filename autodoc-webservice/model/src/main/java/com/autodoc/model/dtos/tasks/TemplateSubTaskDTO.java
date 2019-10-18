package com.autodoc.model.dtos.tasks;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.OneToMany;
import java.util.List;


@Getter
@Setter
@ToString
public class TemplateSubTaskDTO {

    // Constructors

    @NonNull
    private int id;


    // Parameters
    @OneToMany
    private List<Integer> subTasks;
    @NonNull
    private List<Integer> pieces;
    @NonNull
    private String name;
    @NonNull
    private double estimatedTime;

    public TemplateSubTaskDTO(@NonNull int id, List<Integer> subTasks, @NonNull List<Integer> pieces, @NonNull String name, @NonNull double estimatedTime) {
        this.id = id;
        this.subTasks = subTasks;
        this.pieces = pieces;
        this.name = name;
        this.estimatedTime = estimatedTime;
    }

}
