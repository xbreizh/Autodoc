package com.autodoc.model.models.car;

import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.person.client.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private int id;

    private CarModel model;

    @Size(min = 3, max = 7)
    private String registration;

    private Client client;

    private List<Bill> bills;

    private double mileage;
    private String color;


   /* public Car() {
    }


    public Car(@Size(min = 3, max = 7, message = "registration must be between 3 and 7 characters") String registration) {
        this.registration = registration;
    }*/

/*    public Car(int id, int carModelId, String registration, int clientId) {
        this.id = id;
        this.model = model;
        this.registration = registration;
        this.client = client;
    }*/

    @Override
    public String toString() {
        String modelName = "";
        String clientName = "";
        if (model != null) modelName = model.getName();
        if (client != null) clientName = client.getLastName();

        return "Car{" +
                "id=" + id +
                ", model=" + modelName +
                ", registration='" + registration + '\'' +
                ", client=" + clientName +
                ", mileage=" + mileage +
                ", color='" + color + '\'' +
                '}';
    }
}
