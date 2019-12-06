/*
package com.autodoc.model.models.tasks;

import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.pieces.Piece;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "templateTask")
@Getter
@Setter
public class TemplateTask {

    public static final Map<String, SearchType> SEARCH_FIELD = createMap();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NonNull
    @OneToMany
    private List<Piece> pieces;
    @NonNull
    private String name;
    @OneToMany
    private List<Task> taskList;

    private String description;

    private double estimatedTime;





    public static Map<String, SearchType> getSearchField() {
        return SEARCH_FIELD;
    }

    private static Map<String, SearchType> createMap() {
        Map<String, SearchType> result = new HashMap<>();
        result.put("NAME", SearchType.STRING);
        result.put("ID", SearchType.INTEGER);
        return Collections.unmodifiableMap(result);
    }



    @Override
    public String toString() {
        return "TemplateTask{" +
                "id=" + id +
                ", pieces=" + pieces +
                ", name='" + name + '\'' +
                ", taskList=" + taskList +
                ", estimatedTime=" + estimatedTime +
                '}';
    }
}
*/
