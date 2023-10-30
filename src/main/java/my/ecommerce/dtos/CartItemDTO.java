package my.ecommerce.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import my.ecommerce.entities.Cart;
import my.ecommerce.entities.Product;

public class CartItemDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Min(1)
    private int quantity;

    @ManyToOne
    private Cart cart;

}
