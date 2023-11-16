package my.ecommerce.controllers;

import java.security.Principal;
import java.util.List;

import my.ecommerce.entities.Order;
import my.ecommerce.services.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import my.ecommerce.security.ChangePasswordRequest;
import my.ecommerce.services.UserService;

@RestController
@RequestMapping("/profile")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    private final OrderService orderService;

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/my-orders")
    public ResponseEntity<List<Order>> getUserOrders(Principal connectedUser) {
        return ResponseEntity.ok(orderService.findAllByUser(connectedUser));
    }

}
