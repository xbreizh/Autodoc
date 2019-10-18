package com.autodoc.model.dtos.tasks;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
public class TaskDTO {

    // Constructors


    public TaskDTO(@NonNull List<Integer> subTasks, @NonNull int id) {
        this.subTasks = subTasks;
        this.id = id;
    }

    // Parameters
    @NonNull
    private List<Integer> subTasks;

    @NonNull
    private int id;


    // to be used for global offers
    private long globalPrice;

}
