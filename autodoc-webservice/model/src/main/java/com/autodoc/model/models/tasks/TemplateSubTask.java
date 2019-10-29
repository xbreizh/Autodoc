package com.autodoc.model.models.tasks;

import com.autodoc.model.models.pieces.Piece;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "templateSubTask")
@Getter
@Setter
@ToString
public class TemplateSubTask {



    public TemplateSubTask() {

    }

    public TemplateSubTask(@NonNull List<Piece> pieces, @NonNull String name, @NonNull double estimatedTime) {
        this.pieces = pieces;
        this.name = name;
        this.estimatedTime = estimatedTime;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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
