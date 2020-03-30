package com.autodoc.model.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Toy {

    private String name;
    private double price;
    private String brand;

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public void setBrand(String brand) {
        this.brand = brand.toUpperCase();
    }


    // custom builder
    public static class ToyBuilder {
        public ToyBuilder name(String name) {
            this.name = name.toUpperCase();
            return this;
        }

        public ToyBuilder brand(String brand) {
            this.brand = brand.toUpperCase();
            return this;
        }
    }
}
