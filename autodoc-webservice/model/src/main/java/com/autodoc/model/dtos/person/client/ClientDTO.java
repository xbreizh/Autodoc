package com.autodoc.model.dtos.person.client;


import com.autodoc.model.dtos.person.PersonDTO;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Data
@EqualsAndHashCode(callSuper = true)
public class ClientDTO extends PersonDTO {
    public ClientDTO(@NotNull(message = "lastName cannot be null") String lastName, @NotNull(message = "firstName cannot be null") String firstName, @NotNull(message = "phoneNumber1 cannot be null") String phoneNumber1) {
        super(lastName, firstName, phoneNumber1);
    }

    public ClientDTO() {
    }
}
