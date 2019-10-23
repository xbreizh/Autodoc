package com.autodoc.model.dtos.tasks;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
public class TemplateSubTaskDTO {

    // Constructors


    private int id;


    // Parameters

    @NonNull
    private List<Integer> pieces;
    @NonNull
    private String name;
    @NonNull
    private double estimatedTime;

    public TemplateSubTaskDTO(@NonNull List<Integer> pieces, @NonNull String name, @NonNull double estimatedTime) {
        this.pieces = pieces;
        this.name = name;
        this.estimatedTime = estimatedTime;
    }
}
