package my.ecommerce.controllers;

import lombok.RequiredArgsConstructor;
import my.ecommerce.entities.Product;
import my.ecommerce.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProducts(@RequestParam int page, @RequestParam int size, @RequestParam(defaultValue = "") String searchKey) throws IllegalStateException {
        return ResponseEntity.ok().body(productService.findAll(page, size, searchKey));
    }
    @GetMapping("/products/by-category")
    public ResponseEntity<List<Product>> getProducts(@RequestParam Long categoryId) {
        return ResponseEntity.ok().body(productService.findAllByCategory(categoryId));
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

}
