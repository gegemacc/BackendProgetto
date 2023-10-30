package my.ecommerce.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderDTO {

    @NotNull
    private Long id;


}
