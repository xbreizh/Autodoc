package com.autodoc.model;

import com.autodoc.model.person.Employee;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subTask")
public class SubTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;


    @ManyToMany
    private List<Task> tasks;

    @OneToMany
    private List<Piece> pieces;

    @ManyToMany
    private List<Employee> employees;


}
