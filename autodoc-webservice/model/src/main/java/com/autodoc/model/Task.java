package com.autodoc.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @ManyToMany
    private List<Bill> bills;


    @ManyToMany
    private List<SubTask> subTasks;

    private long globalPrice;


}
