package com.autodoc.model.dtos.tasks;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Setter
public class TaskDTO {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


   /* @ManyToMany
    private List<Integer> pieces;*/


    private String name;


    private String description;

    private double estimatedTime;


    //  private String template;

   // private double price;

    public TaskDTO(String name, String description, double estimatedTime/*, double price, *//*String template)*/) {
        this.name = name;
        this.description = description;
        this.estimatedTime = estimatedTime;
        // this.price = price;
        //     this.template = template;
    }

    public TaskDTO() {
    }


    @Override
    public String toString() {
        return "TaskDTO{" +
                "id=" + id +
              //  ", pieces=" + pieces +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", estimatedTime=" + estimatedTime +
                //   ", template='" + template + '\'' +
                //  ", price=" + price +
                '}';
    }
}

