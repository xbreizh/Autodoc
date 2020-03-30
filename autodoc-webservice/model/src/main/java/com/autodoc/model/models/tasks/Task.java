package com.autodoc.model.models.tasks;

import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.bill.Bill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "task")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {


    public static final Map<String, SearchType> SEARCH_FIELD = createMap();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToMany(mappedBy = "tasks")
    private List<Bill> bills;

    @NotNull
    private String name;


    private String description;

    @NotNull
    private double estimatedTime;

    public static Map<String, SearchType> getSearchField() {
        return SEARCH_FIELD;
    }

    private static Map<String, SearchType> createMap() {
        Map<String, SearchType> result = new HashMap<>();
        result.put("NAME", SearchType.STRING);
        result.put("ID", SearchType.INTEGER);
        result.put("TEMPLATE", SearchType.BOOLEAN);
        return Collections.unmodifiableMap(result);
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public void setDescription(String description) {
        this.description = description.toUpperCase();
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", estimatedTime=" + estimatedTime +
                '}';
    }

    public static class TaskBuilder {
        public TaskBuilder name(String name) {
            this.name = name.toUpperCase();
            return this;
        }

        public TaskBuilder description(String description) {
            this.description = description.toUpperCase();
            return this;
        }

    }
}
