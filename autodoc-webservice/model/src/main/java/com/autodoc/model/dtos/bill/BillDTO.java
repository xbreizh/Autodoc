package com.autodoc.model.dtos.bill;


import com.autodoc.model.enums.Status;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class BillDTO {

    // Constructors

    @NonNull
    private int id;


    // Parameters
    @FutureOrPresent(message = "date should nto be in the past")
    private Date date;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @Min(value = 1, message = "carId cannot be null")
    private int carId;

    @Min(value = 1, message = "clientId cannot be null")
    private int clientId;

    @Min(value = 1, message = "employeeId cannot be null")
    private int employeeId;

    @Min(value = 1, message = "total cannot be null")
    private double total;

    private static final double vat = 00.195;

    private double discount;


}
