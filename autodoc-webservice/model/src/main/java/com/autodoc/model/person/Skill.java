package com.autodoc.model.person;

import lombok.Getter;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    private String name;

    @ManyToOne
    private SkillCategory skillCategory;
}
