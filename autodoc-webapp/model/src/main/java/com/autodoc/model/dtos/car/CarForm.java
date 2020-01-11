package com.autodoc.model.dtos.car;

import com.autodoc.model.models.person.client.Client;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CarForm {

    int id;

    private Client client;

    @NotNull
    @Size(min = 5, max = 12, message = "{registration.size}")
    private String registration;

    public CarForm() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        String clientDetails = "no client details";
        if (client != null) clientDetails = client.getFirstName();
        return "CarForm{" +
                "id=" + id +
                ", registration='" + registration + '\'' +
                ", client=" + clientDetails +
                '}';
    }
}