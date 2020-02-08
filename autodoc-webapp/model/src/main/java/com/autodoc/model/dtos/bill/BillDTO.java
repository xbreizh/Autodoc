package com.autodoc.model.dtos.bill;


import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class BillDTO {

    private static final double VAT = 00.195;
    private int id;


    @FutureOrPresent(message = "date should nto be in the past")
    private Date date;

    @NotNull
    private String status;

    @NotNull
    private List<Integer> tasks;

    @NotNull(message = "car Registration cannot be null")
    private String registration;

    @Min(value = 1, message = "clientId cannot be null")
    private int clientId;

    @Min(value = 1, message = "employeeId cannot be null")
    private int employeeId;

    @NotNull
    private double vat;

    @Min(value = 1, message = "total cannot be null")
    private double total;
    private double discount;
    private String comments;

    public BillDTO() {
    }

    public BillDTO(int id, @FutureOrPresent(message = "date should nto be in the past") Date date, @NotNull String status, @NotNull(message = "car Registration cannot be null") String registration, @Min(value = 1, message = "clientId cannot be null") int clientId, @Min(value = 1, message = "employeeId cannot be null") int employeeId, @Min(value = 1, message = "total cannot be null") double total, double discount) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.registration = registration;
        this.clientId = clientId;
        this.employeeId = employeeId;
        this.total = total;
        this.discount = discount;
    }
}
