package com.autodoc.model.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private long id;

    private String title;

    private String author;

    public Book() {
    }

    public Book(long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
}