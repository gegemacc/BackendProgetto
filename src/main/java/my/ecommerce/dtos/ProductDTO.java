package my.ecommerce.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import my.ecommerce.enums.ProductCategory;
import java.math.BigDecimal;
@Data
public class ProductDTO {
    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
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
