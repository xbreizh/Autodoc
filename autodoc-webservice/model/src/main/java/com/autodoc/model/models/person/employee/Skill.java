package com.autodoc.model.models.person.employee;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "skill")
@Setter @Getter @ToString
public class Skill implements Serializable {

    // Constructors

    public Skill() {
    }

    public Skill(String name, SkillCategory skillCategory) {
        this.name = name;
        this.skillCategory = skillCategory;
    }

    // Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private int id;

    @NonNull
    private String name;

    @ManyToOne
    @NonNull
    private SkillCategory skillCategory;
}
