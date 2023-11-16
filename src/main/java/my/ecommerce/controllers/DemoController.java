package my.ecommerce.controllers;

import lombok.RequiredArgsConstructor;
import my.ecommerce.dtos.CategoryCreateDTO;
import my.ecommerce.dtos.ProductCreateDTO;
import my.ecommerce.entities.Product;
import my.ecommerce.security.RegisterRequest;
import my.ecommerce.services.AuthenticationService;
import my.ecommerce.services.CategoryService;
import my.ecommerce.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class DemoController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final AuthenticationService authenticationService;

    @GetMapping("/demo")
    public ResponseEntity<?> addProduct() {
        List<String> categories = new ArrayList<>();
        categories.add("Elettronica");
        categories.add("Abbigliamento");
        categories.add("Libri");
        categories.add("Casa");
        categories.add("Giochi");
        CategoryCreateDTO categoryCreateDTO = new CategoryCreateDTO();
        for(String categoryName: categories) {
            categoryCreateDTO.setName(categoryName);
            this.categoryService.addCategory(categoryCreateDTO);
        }
        List<ProductCreateDTO> products = new ArrayList<>();
        products.add(ProductCreateDTO.builder().name("Iphone 15 128GB").category(categoryService.getCategoryById(1L)).image("https://i.ibb.co/cx7TJ59/1.jpg").stock(3).price(BigDecimal.valueOf(899.99)).description("iPhone 15 ha un robusto design realizzato in vetro a infusione di colore e in alluminio.").build());
        products.add(ProductCreateDTO.builder().name("Maglietta Nike").category(categoryService.getCategoryById(2L)).image("https://i.ibb.co/wwLrn00/2.webp").stock(2).price(BigDecimal.valueOf(19.99)).description("T-Shirt Nike colore nero e taglia S").build());
        products.add(ProductCreateDTO.builder().name("1984").category(categoryService.getCategoryById(3L)).image("https://i.ibb.co/fvqkRGX/3.jpg").stock(1).price(BigDecimal.valueOf(11.50)).description("Il potere non è un mezzo, è un fine").build());
        products.add(ProductCreateDTO.builder().name("Quadro").category(categoryService.getCategoryById(4L)).image("https://i.ibb.co/jL6VQ8H/5.jpg").stock(5).price(BigDecimal.valueOf(35.70)).description("Dimensioni totali: 200 x 100 cm (lunghezza x altezza).").build());
        products.add(ProductCreateDTO.builder().name("LEGO 60368 City Esploratore Artico").category(categoryService.getCategoryById(5L)).image("https://i.ibb.co/TPjJLfq/4.jpg").stock(4).price(BigDecimal.valueOf(129.00)).description("Il set LEGO City Esploratore Artico include: una grande nave giocattolo galleggiante.").build());
        for(ProductCreateDTO product: products) {
            this.productService.addProduct(product);
        }
        RegisterRequest admin = new RegisterRequest("Alessandro","Mendicino","admin@email.com","1234");
        this.authenticationService.registerAdminDemo(admin);
        return ResponseEntity.ok().build();
    }
}
