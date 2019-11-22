package com.autodoc.model.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Manufacturer {

    private int id;

    private String name;

    public Manufacturer(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public Manufacturer() {
    }


    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
