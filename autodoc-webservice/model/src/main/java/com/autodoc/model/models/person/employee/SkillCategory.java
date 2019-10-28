package com.autodoc.model.models.person.employee;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "skillCategory")
@Setter
@Getter
@ToString
public class SkillCategory {


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

    @OneToMany(mappedBy = "skillCategory", cascade = CascadeType.REMOVE)
    private List<Skill> skills;

    @NonNull
    private String name;
}
