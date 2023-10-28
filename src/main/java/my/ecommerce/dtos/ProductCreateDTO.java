package my.ecommerce.dtos;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import my.ecommerce.enums.ProductCategory;

import java.math.BigDecimal;
@Data
public class ProductCreateDTO {
    @NotNull
    private String name;

    @NotNull
    private ProductCategory category;

    @NotNull
    private BigDecimal price;

    @NotNull
    private String image;

    @NotNull
    @Min(1)
    private int stock;

    @NotNull
    private String description;
}
