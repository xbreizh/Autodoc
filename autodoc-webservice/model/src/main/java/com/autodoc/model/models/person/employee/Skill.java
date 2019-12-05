/*
package com.autodoc.model.models.person.employee;

import com.autodoc.model.enums.SearchType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "skill")
@Setter
@Getter
@ToString
public class Skill {

    public static Map<String, SearchType> getSearchField() {
        return SEARCH_FIELD;
    }

    public static final Map<String, SearchType> SEARCH_FIELD = createMap();

    private static Map<String, SearchType> createMap() {
        Map<String, SearchType> result = new HashMap<>();
        result.put("NAME", SearchType.STRING);
        result.put("skillCategory.name", SearchType.STRING);
        result.put("ID", SearchType.INTEGER);
        return Collections.unmodifiableMap(result);
    }

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SkillCategory skillCategory;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<Employee> employees;
}
*/
