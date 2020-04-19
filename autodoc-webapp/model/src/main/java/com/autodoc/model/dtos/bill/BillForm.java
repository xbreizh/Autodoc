package com.autodoc.model.dtos.bill;

import com.autodoc.model.dtos.PieceList;
import com.autodoc.model.dtos.TaskList;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class BillForm {


    private static final double VAT = 00.195;
    private int id;

    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
    @NotNull
    private Date dateReparation;

    @NotEmpty
    private String status;

    private String paymentType;

    private TaskList tasks;

    private PieceList pieces;


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
    private int discount;

    private String comments;


    @Override
    public String toString() {
        return "BillForm{" +
                "id=" + id +
                ", dateReparation=" + dateReparation +
                ", status='" + status + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", tasks=" + tasks +
                ", pieceList=" + pieces +
                ", carRegistration='" + carRegistration + '\'' +
                ", clientId=" + clientId +
                ", employeeLogin='" + employeeLogin + '\'' +
                ", vat=" + vat +
                ", total=" + total +
                ", discount=" + discount +
                ", comments='" + comments + '\'' +
                '}';
    }
}










