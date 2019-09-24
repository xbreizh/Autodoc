package com.autodoc.model.models.tasks;

import com.autodoc.model.models.pieces.Piece;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "emplateSubTask")
@Getter @Setter @ToString
public class TemplateSubTask implements Serializable {

    // Constructors

    public TemplateSubTask() {

    }



    // Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private int id;

    @OneToMany
    private List<SubTask> subTasks;

    @NonNull
    @OneToMany
    private List<Piece> pieces;

    @NonNull
    private String name;

    @NonNull
    private double estimatedTime;

}