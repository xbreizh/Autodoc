package com.autodoc.model.models.tasks;

import com.autodoc.model.models.person.employee.Employee;
import com.autodoc.model.models.pieces.Piece;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subTask")
@Getter
@Setter
@ToString
public class SubTask {

    // Constructors


    public SubTask() {
    }


    // Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;


    @ManyToMany(cascade = CascadeType.REMOVE)
    @NonNull
    private List<Task> tasks;

    @OneToMany(cascade = CascadeType.REMOVE)
    @NonNull
    private List<Piece> pieces;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<Employee> employees;

    @ManyToOne
    @NonNull
    private TemplateSubTask templateSubTask;

    @NonNull
    private String name;

    @NonNull
    private double estimatedTime;


}
