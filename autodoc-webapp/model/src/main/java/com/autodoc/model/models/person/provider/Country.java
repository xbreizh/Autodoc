package com.autodoc.model.models.person.provider;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class Country {


    private int id;

    @NonNull
    private String name;


    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
