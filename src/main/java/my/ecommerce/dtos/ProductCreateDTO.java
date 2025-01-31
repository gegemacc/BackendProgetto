package my.ecommerce.dtos;

import lombok.*;
import my.ecommerce.entities.Category;

import java.math.BigDecimal;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCreateDTO {

    private String name;

    private Category category;

    private BigDecimal price;

    private String image;

    private int stock;

    private String description;
}
