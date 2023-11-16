package my.ecommerce.controllers;

import lombok.RequiredArgsConstructor;
import my.ecommerce.dtos.CategoryCreateDTO;
import my.ecommerce.dtos.ProductCreateDTO;
import my.ecommerce.entities.Category;
import my.ecommerce.entities.Product;
import my.ecommerce.services.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getCategories() throws IllegalStateException {
        return ResponseEntity.ok().body(categoryService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable("id") Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

}
