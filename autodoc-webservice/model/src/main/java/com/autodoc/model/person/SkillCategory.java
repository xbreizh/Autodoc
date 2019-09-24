package com.autodoc.model.person;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "skillCategory")
@Setter @Getter @ToString
public class SkillCategory implements Serializable {



    // Constructor


    public SkillCategory() {
    }

    public SkillCategory(String name) {
        this.name = name;
    }


    // Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @OneToMany
    private List<Skill> skills;

    private String name;
}
