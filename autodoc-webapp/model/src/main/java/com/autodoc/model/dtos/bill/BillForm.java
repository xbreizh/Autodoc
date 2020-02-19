package com.autodoc.model.dtos.bill;

import com.autodoc.model.dtos.TaskList;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
public class BillForm {


    private static final double VAT = 00.195;
    private int id;


    //@FutureOrPresent(message = "date should nto be in the past")
    // @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateReparation;

    @NotEmpty
    private String status;

    private TaskList tasks;


    @NotEmpty(message = "car  cannot be null")
    private String carRegistration;

    @Min(value = 1, message = "client cannot be null")
    private int clientId;

    @NotEmpty(message = "employee cannot be null")
    private String employeeLogin;

    @Min(value = 1, message = "VAT cannot be null")
    private double vat;

   // @Min(value = 1, message = "total cannot be null")
    private double total;

    @Max(100)
    private double discount;

    //@Max(1000)
    private String comments;


    @Override
    public String toString() {
        return "BillForm{" +
                "id=" + id +
                ", date=" + dateReparation +
                ", status='" + status + '\'' +
                ", tasks=" + tasks +
                ", CarRegistration='" + carRegistration + '\'' +
                ", clientId=" + clientId +
                ", employeeLogin='" + employeeLogin + '\'' +
                ", vat=" + vat +
                ", total=" + total +
                ", discount=" + discount +
                ", comments='" + comments + '\'' +
                '}';
    }
}










