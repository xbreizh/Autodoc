package com.autodoc.model.dtos.bill;

import com.autodoc.model.models.tasks.Task;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class BillForm {


    private static final double VAT = 00.195;
    private int id;


    @FutureOrPresent(message = "date should nto be in the past")
    private Date date;

    @NotEmpty
    private String status;

    private List<Task> tasks;


    @NotEmpty(message = "car  cannot be null")
    private String CarRegistration;

    @Min(value = 1, message = "client cannot be null")
    private int clientId;

    @NotEmpty(message = "employee cannot be null")
    private String employeeLogin;

    @Min(value = 1, message = "VAT cannot be null")
    private double vat;

    @Min(value = 1, message = "total cannot be null")
    private double total;

    @Max(100)
    private double discount;

    public void addTask(Task task) {
        tasks.add(task);
    }


    @Override
    public String toString() {
        return "BillForm{" +
                "id=" + id +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", tasks=" + tasks +
                ", car registration=" + CarRegistration +
                ", client=" + clientId +
                ", employee=" + employeeLogin +
                ", vat=" + vat +
                ", total=" + total +
                ", discount=" + discount +
                '}';
    }
}










