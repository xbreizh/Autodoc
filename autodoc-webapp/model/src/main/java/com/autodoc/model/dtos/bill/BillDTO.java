package com.autodoc.model.dtos.bill;


import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class BillDTO {

    private static final double VAT = 00.195;
    private int id;


    private String dateReparation;

    @NotNull
    private String status;

    private String paymentType;

    @NotNull
    private List<Integer> tasks;

    private List<Integer> pieces;

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

    @Override
    public String toString() {
        return "BillDTO{" +
                "id=" + id +
                ", dateReparation='" + dateReparation + '\'' +
                ", status='" + status + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", tasks=" + tasks +
                ", pieces=" + pieces +
                ", registration='" + registration + '\'' +
                ", clientId=" + clientId +
                ", employeeId=" + employeeId +
                ", vat=" + vat +
                ", total=" + total +
                ", discount=" + discount +
                ", comments='" + comments + '\'' +
                '}';
    }
}
