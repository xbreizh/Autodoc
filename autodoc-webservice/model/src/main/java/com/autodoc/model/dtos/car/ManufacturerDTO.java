package com.autodoc.model.dtos.car;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class ManufacturerDTO extends ResourceSupport {


    @NotNull(message = "name cannot be null")
    @Size(min = 2, max = 50, message = "name should have between 2 and 50 characters")
    private String name;

    Link link = new Link("http://localhost:8080/spring-security-rest/api/customers/10A");

    public ManufacturerDTO(@NotNull(message = "name cannot be null") @Size(min = 2, max = 50, message = "name should have between 2 and 50 characters") String name) {
        this.name = name;
    }

    public ManufacturerDTO() {
    }
}
