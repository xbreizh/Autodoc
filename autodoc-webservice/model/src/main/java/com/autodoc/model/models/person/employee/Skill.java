package com.autodoc.model.models.person.employee;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "skill")
@Setter
@Getter
@ToString
public class Skill {



    public Skill() {
    }

    public Skill(String name, SkillCategory skillCategory ){
        this.name = name;
        this.skillCategory = skillCategory;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @NonNull
    private String name;

    @ManyToOne
   // @NotNull
    private SkillCategory skillCategory;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<Employee> employees;
}
