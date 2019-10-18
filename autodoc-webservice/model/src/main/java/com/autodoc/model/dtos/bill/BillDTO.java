package com.autodoc.model.dtos.bill;


import com.autodoc.model.enums.Status;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Getter
@Setter
@ToString
public class BillDTO {

    // Constructors

    @NonNull
    private int id;


    // Parameters
    @NonNull
    private Date date;
    @NonNull
    @Enumerated(EnumType.STRING)
    private Status status;
    @NonNull
    private int carId;
    @NonNull
    private int clientId;
    @NonNull
    private int employeeId;
    @NonNull
    private double total;
    @NonNull
    private double vat;
    @NonNull
    private double discount;

    public BillDTO(@NonNull int id, @NonNull Date date, @NonNull Status status, @NonNull int carId, @NonNull int clientId, @NonNull int employeeId, @NonNull double total, @NonNull double vat, @NonNull double discount) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.carId = carId;
        this.clientId = clientId;
        this.employeeId = employeeId;
        this.total = total;
        this.vat = vat;
        this.discount = discount;
    }


}
