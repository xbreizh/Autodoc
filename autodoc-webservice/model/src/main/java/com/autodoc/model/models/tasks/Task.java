package com.autodoc.model.models.tasks;

import com.autodoc.model.enums.SearchType;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.pieces.Piece;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "task")
@Getter
@Setter
public class Task {


    public static final Map<String, SearchType> SEARCH_FIELD = createMap();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


  /*  @ManyToMany(mappedBy = "tasks")
    private List<Piece> pieces;*/

    @ManyToMany(mappedBy = "tasks")
    private List<Bill> bills;

    @NotNull
    private String name;


    private String description;

    @NotNull
    private double estimatedTime;


    @NotNull
    private boolean template;

  /*  @NotNull
    private double price;*/

    public Task(String name, String description, double estimatedTime,/* double price,*/ boolean template) {
        this.name = name;
        this.description = description;
        this.estimatedTime = estimatedTime;
       // this.price = price;
        this.template = template;
    }

    public Task() {
    }

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


    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
              //  ", pieces=" + pieces +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", estimatedTime=" + estimatedTime +
                ", template=" + template +
               // ", price=" + price +
                '}';
    }
}
