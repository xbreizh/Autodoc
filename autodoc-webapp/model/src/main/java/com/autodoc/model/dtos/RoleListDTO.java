package com.autodoc.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleListDTO {

    String role;

    public RoleListDTO(String role) {
        this.role = role;
    }

    public RoleListDTO() {
    }
}
