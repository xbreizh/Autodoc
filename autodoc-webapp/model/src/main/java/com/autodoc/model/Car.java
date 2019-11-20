package com.autodoc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car {

    private int id;

    private CarModel model;

    private String registration;

    private Client client;


    public Car() {
    }


    public Car(int id, int carModelId, String registration, int clientId) {
        this.id = id;
        this.model = model;
        this.registration = registration;
        this.client = client;
    }

    @Override
    public String toString() {
        String modelName ="";
        String clientName = "";
        if(model!=null)modelName = model.getName();
        if(client!=null)clientName = client.getLastName();

        return "Car{" +
                "id=" + id +
                ", model=" + modelName +
                ", registration='" + registration + '\'' +
                ", client=" + clientName +
                '}';
    }
}
