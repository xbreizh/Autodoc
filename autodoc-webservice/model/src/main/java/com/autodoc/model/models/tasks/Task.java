package com.autodoc.model.models.tasks;

import com.autodoc.model.models.Bill;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "task")
@Getter @Setter @ToString
public class Task implements Serializable {

    // Constructors


    public Task() {

    }

    public Task(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    // Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private int id;

    @ManyToMany(mappedBy = "tasks", cascade = CascadeType.REMOVE)
    private List<Bill> bills;


    @NonNull
    @ManyToMany(mappedBy = "tasks", cascade = CascadeType.REMOVE)
    private List<SubTask> subTasks;
    // to be used for global offers
    private long globalPrice;

}
