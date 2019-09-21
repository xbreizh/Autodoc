package com.autodoc.model;


import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "name")
    private String registration;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", registration='" + registration + '\'' +
                '}';
    }
}
