package com.autodoc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "templateSubTask")
@Getter @Setter @ToString
public class TemplateSubTask implements Serializable {

    // Constructors

    public TemplateSubTask() {

    }

    public TemplateSubTask(String name, double estimatedTime) {
        this.name = name;
        this.estimatedTime = estimatedTime;
    }

    // Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @OneToMany
    private List<SubTask> subTasks;

    @OneToMany
    private List<Piece> pieces;

    private String name;

    private double estimatedTime;

}
