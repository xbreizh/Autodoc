package com.autodoc.model.models.tasks;

import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.pieces.Piece;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "templateSubTask")
@Getter
@Setter
@ToString
public class TemplateSubTask {

    public static Map<String, SearchType> getSearchField() {
        return SEARCH_FIELD;
    }

    public static final Map<String, SearchType> SEARCH_FIELD = createMap();

    private static Map<String, SearchType> createMap() {
        Map<String, SearchType> result = new HashMap<>();
        result.put("NAME", SearchType.STRING);
        result.put("ID", SearchType.INTEGER);
        return Collections.unmodifiableMap(result);
    }

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
