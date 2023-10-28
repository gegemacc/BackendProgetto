package my.ecommerce.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.ecommerce.dtos.ProductCreateDTO;
import my.ecommerce.entities.Product;
import my.ecommerce.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/admin/products/new")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody ProductCreateDTO productCreateDTO) {
        return ResponseEntity.ok(productService.addProduct(productCreateDTO));
    }

    @PutMapping("/admin/products/{id}/edit")
    public ResponseEntity<Product> editProduct(@PathVariable("id") Long id, @Valid @RequestBody ProductCreateDTO productCreateDTO) {
        return ResponseEntity.ok(productService.editProduct(id, productCreateDTO));
    }

    @DeleteMapping("/admin/products/{id}/delete")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
