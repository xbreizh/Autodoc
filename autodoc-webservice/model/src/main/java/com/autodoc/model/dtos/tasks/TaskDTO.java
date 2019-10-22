package com.autodoc.model.dtos.tasks;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
public class TaskDTO {

    // Constructors

    // Parameters
    private int id;
    @NotNull(message = "name cannot be null")
    String name;

    @NonNull
    private List<Integer> subTasks;


    // to be used for global offers
    @Min(value = 1, message = "globalPrice cannot be null")
    private long globalPrice;

}
