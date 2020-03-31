package com.autodoc.model.dtos.bill;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO {

    private static final double VAT = 00.195;
    private int id;

    private String dateReparation;

    @NotNull
    private String status;

    @NotNull
    private List<Integer> tasks;

    @NotNull
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


}
