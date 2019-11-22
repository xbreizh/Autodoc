package com.autodoc.model.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class EmployeeDTO {


  /*  @NotNull(message = "role should not be null")
    @Enumerated(EnumType.STRING)*/
    private List<String> roles;

    @PastOrPresent
    private Date startDate;

    @NotNull(message = "login cannot be null")
    private String login;

    @PastOrPresent
    private Date lastConnection;

    public EmployeeDTO(List<String> roles, @PastOrPresent Date startDate, @NotNull(message = "login cannot be null") String login, @PastOrPresent Date lastConnection) {
        this.roles = roles;
        this.startDate = startDate;
        this.login = login;
        this.lastConnection = lastConnection;
    }

    public EmployeeDTO() {
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "roles=" + roles +
                ", startDate=" + startDate +
                ", login='" + login + '\'' +
                ", lastConnection=" + lastConnection +
                '}';

    }
}
