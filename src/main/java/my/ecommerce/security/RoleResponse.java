package my.ecommerce.security;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;
import my.ecommerce.enums.Role;

@Data
@Builder
public class RoleResponse {

    @Enumerated(EnumType.STRING)
    private Role role;
}
