package my.ecommerce.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.ecommerce.dtos.CategoryCreateDTO;
import my.ecommerce.dtos.ProductCreateDTO;
import my.ecommerce.dtos.UserDTO;
import my.ecommerce.entities.Order;
import my.ecommerce.entities.Product;
import my.ecommerce.entities.User;
import my.ecommerce.services.CategoryService;
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
    private final CategoryService categoryService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() throws IllegalStateException {
        return ResponseEntity.ok().body(userService.findAllUsers());
    }

    @PostMapping("/products/new")
    public ResponseEntity<Product> addProduct(@RequestBody ProductCreateDTO productCreateDTO) {
        return ResponseEntity.ok().body(productService.addProduct(productCreateDTO));
    }

    @PutMapping("/products/{id}/edit")
    public ResponseEntity<ProductCreateDTO> editProduct(@PathVariable("id") Long id, @Valid @RequestBody ProductCreateDTO productCreateDTO) {
        return ResponseEntity.ok(productService.editProduct(id, productCreateDTO));
    }

    @DeleteMapping("/products/{id}/delete")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/categories/new")
    public ResponseEntity<?> addCategory(@RequestBody CategoryCreateDTO categoryCreateDTO) {
        categoryService.addCategory(categoryCreateDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("categories/{id}/edit")
    public ResponseEntity<?> editCategory(@PathVariable("id") Long id, @RequestBody CategoryCreateDTO categoryCreateDTO) {
        categoryService.editCategory(id,categoryCreateDTO);
        return ResponseEntity.ok().build();
    }

}
