package my.ecommerce.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.ecommerce.dtos.ProductDTO;
import my.ecommerce.dtos.UserDTO;
import my.ecommerce.entities.Product;
import my.ecommerce.enums.Role;
import my.ecommerce.services.ProductService;
import my.ecommerce.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/users")
    public ResponseEntity<Page<UserDTO>> getUsers(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok().body(userService.findAll(page, size));
    }

    @GetMapping("/products")
    public ResponseEntity<Page<ProductDTO>> getProducts(@RequestParam int page, @RequestParam int size) throws IllegalStateException {
        return ResponseEntity.ok().body(productService.findAll(page, size));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping("/products/new")
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.addProduct(productDTO));
    }

    @PutMapping("/products/{id}/edit")
    public ResponseEntity<ProductDTO> editProduct(@PathVariable("id") Long id, @Valid @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.editProduct(id, productDTO));
    }

    @DeleteMapping("/products/{id}/delete")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
