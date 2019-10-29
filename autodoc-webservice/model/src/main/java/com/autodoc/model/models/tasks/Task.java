package com.autodoc.model.models.tasks;

import com.autodoc.model.models.bill.Bill;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "task")
@Data
@NoArgsConstructor
public class Task {



    @NotNull(message = "name cannot be null")
    String name;


    public Task(@NotNull(message = "name cannot be null") String name, @NonNull List<SubTask> subTasks) {
        this.name = name;
        this.subTasks = subTasks;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToMany(mappedBy = "tasks", cascade = CascadeType.REMOVE)
    private transient List<Bill> bills;


    @NonNull
    @ManyToMany(mappedBy = "tasks", cascade = CascadeType.REMOVE)
    private List<SubTask> subTasks;
    // to be used for global offers
    private long globalPrice;

    public Task(@NotNull(message = "name cannot be null") String name, @NonNull List<SubTask> subTasks, long globalPrice) {
        this.name = name;
        this.subTasks = subTasks;
        this.globalPrice = globalPrice;
    }
}
