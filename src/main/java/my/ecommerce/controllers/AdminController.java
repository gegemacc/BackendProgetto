package my.ecommerce.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.ecommerce.dtos.ProductCreateDTO;
import my.ecommerce.dtos.UserDTO;
import my.ecommerce.entities.Order;
import my.ecommerce.entities.User;
import my.ecommerce.services.OrderService;
import my.ecommerce.services.ProductService;
import my.ecommerce.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserService userService;
    private final OrderService orderService;
    private final ProductService productService;
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<List<Order>> getOrdersByUser() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() throws IllegalStateException {
        return ResponseEntity.ok().body(userService.findAllUsers());
    }

}
