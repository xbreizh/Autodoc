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

    // Constructors

    @NotNull(message = "name cannot be null")
    String name;


    public Task(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    // Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToMany(mappedBy = "tasks", cascade = CascadeType.REMOVE)
    private List<Bill> bills;


    @NonNull
    @ManyToMany(mappedBy = "tasks", cascade = CascadeType.REMOVE)
    private List<SubTask> subTasks;
    // to be used for global offers
    private long globalPrice;

    public Task(@NotNull(message = "name cannot be null") String name, List<Bill> bills, @NonNull List<SubTask> subTasks, long globalPrice) {
        this.name = name;
        this.bills = bills;
        this.subTasks = subTasks;
        this.globalPrice = globalPrice;
    }
}
