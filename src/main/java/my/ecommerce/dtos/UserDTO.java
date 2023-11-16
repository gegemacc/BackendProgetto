package my.ecommerce.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import my.ecommerce.enums.Role;

import java.util.List;

@Data
public class UserDTO {

    @NotNull
    @Id
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;
}
