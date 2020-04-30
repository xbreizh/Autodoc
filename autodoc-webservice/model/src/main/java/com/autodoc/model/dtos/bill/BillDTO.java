package com.autodoc.model.dtos.bill;


import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class BillDTO {

    public static final double VAT = 00.195;
    private int id;

    private String dateReparation;

    @NotNull
    private String status;

    private String paymentType;

    //@NotNull
    private List<Integer> tasks;

   // @NotNull
    private List<Integer> pieces;

    @NotNull(message = "car Registration cannot be null")
    private String registration;

    @Min(value = 1, message = "clientId cannot be null")
    private int clientId;

    @Min(value = 1, message = "employeeId cannot be null")
    private int employeeId;


   // @Min(value = 1, message = "total cannot be null")
    private double total;
    @Min(value = 0, message = "invalid value for discount")
    @Max(value = 100, message = "discount max is 100")
    private double discount;


    private String comments;


}
