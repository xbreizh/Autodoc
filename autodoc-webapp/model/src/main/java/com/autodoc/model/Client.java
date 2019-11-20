package com.autodoc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client extends Person {


    private int carModelId;


    private String registration;


    private int clientId;


    public Client() {
    }


    public Client(int id, String firstName, String lastName, String phoneNumber1, int carModelId, String registration, int clientId) {
        super(id, firstName, lastName, phoneNumber1);
        this.carModelId = carModelId;
        this.registration = registration;
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Client{" +
                "carModelId=" + carModelId +
                ", registration='" + registration + '\'' +
                ", clientId=" + clientId +
                '}';
    }
}
