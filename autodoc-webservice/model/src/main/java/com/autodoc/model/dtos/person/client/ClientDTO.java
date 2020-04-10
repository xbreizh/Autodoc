package com.autodoc.model.dtos.person.client;


import com.autodoc.model.dtos.person.PersonDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@NoArgsConstructor
@Generated
public class ClientDTO extends PersonDTO {


    @Override
    public String toString() {
        return "ClientDTO{} " + super.toString();
    }

    @Builder
    public ClientDTO(int id, @NonNull String firstName, @NonNull String lastName, @NonNull String phoneNumber, String email1, String email2, String website, String company, String address) {
        super(id,  firstName.toUpperCase(),  lastName.toUpperCase(),phoneNumber.toUpperCase());
    }
}
