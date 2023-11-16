package my.ecommerce.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import my.ecommerce.entities.Cart;
import my.ecommerce.entities.Product;

public class CartItemDTO {

    public Long id;
    public String productID;
    public int quantity;

}
