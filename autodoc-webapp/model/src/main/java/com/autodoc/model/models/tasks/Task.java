/*
package com.autodoc.model.models.tasks;

import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.bill.Bill;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "task")
@Data
@NoArgsConstructor
public class Task {


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

    @NotNull(message = "name cannot be null")
    String name;


    public Task(@NotNull(message = "name cannot be null") String name, @NonNull List<SubTask> subTasks) {
        this.name = name;
        this.subTasks = subTasks;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToMany(mappedBy = "tasks", cascade = CascadeType.REMOVE)
    private transient List<Bill> bills;


    @NonNull
    @ManyToMany(mappedBy = "tasks", cascade = CascadeType.REMOVE)
    private List<SubTask> subTasks;
    // to be used for global offers
    private long globalPrice;

    public Task(@NotNull(message = "name cannot be null") String name, @NonNull List<SubTask> subTasks, long globalPrice) {
        this.name = name;
        this.subTasks = subTasks;
        this.globalPrice = globalPrice;
    }
}
*/
