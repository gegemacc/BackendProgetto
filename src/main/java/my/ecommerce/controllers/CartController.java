package my.ecommerce.controllers;

import lombok.RequiredArgsConstructor;
import my.ecommerce.dtos.OrderDTO;
import my.ecommerce.entities.Cart;
import my.ecommerce.entities.Order;
import my.ecommerce.services.CartService;
import my.ecommerce.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<Cart> getCart(Principal connectedUser) {
        return ResponseEntity.ok(cartService.getCart(connectedUser));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addItemToCart(@RequestParam Long id, @RequestParam int quantity,  Principal connectedUser) {
        cartService.addToCart(id, quantity, connectedUser);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit")
    public ResponseEntity<Cart> editItemInCart(@RequestParam Long cartItemId, @RequestParam int oldQuantity, @RequestParam int newQuantity, Principal connectedUser) {
        cartService.editCart(cartItemId, oldQuantity, newQuantity, connectedUser);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteItemFromCart(@RequestParam Long cartItemId, Principal connectedUser) {
        cartService.deleteFromCart(cartItemId, connectedUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/checkout")
    public ResponseEntity<Order> placeOrder(@RequestBody OrderDTO orderDTO, Principal connectedUser) {
        return ResponseEntity.ok(orderService.placeOrder(orderDTO, connectedUser));
    }

}
