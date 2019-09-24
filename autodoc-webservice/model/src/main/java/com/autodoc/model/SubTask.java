package com.autodoc.model;

import com.autodoc.model.person.Employee;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "subTask")
@Getter @Setter @ToString
public class SubTask implements Serializable {

    // Constructors


    public SubTask() {
    }

    public SubTask(TemplateSubTask templateSubTask) {
        this.templateSubTask = templateSubTask;

        // getting elements from the template
        this.name = templateSubTask.getName();
        this.estimatedTime = templateSubTask.getEstimatedTime();
        this.pieces = templateSubTask.getPieces();
    }

    // Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;


    @ManyToMany
    private List<Task> tasks;

    @OneToMany
    private List<Piece> pieces;

    @ManyToMany
    private List<Employee> employees;

    @ManyToOne
    private TemplateSubTask templateSubTask;

    private String name;

    private double estimatedTime;


}
