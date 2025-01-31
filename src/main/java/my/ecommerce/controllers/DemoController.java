package my.ecommerce.controllers;

import lombok.RequiredArgsConstructor;
import my.ecommerce.dtos.CategoryCreateDTO;
import my.ecommerce.dtos.ProductCreateDTO;
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
        categories.add("John Deere");
        categories.add("Fendt");
        categories.add("New Holland");
        categories.add("Laverda");
        categories.add("Case");
        CategoryCreateDTO categoryCreateDTO = new CategoryCreateDTO();
        for(String categoryName: categories) {
            categoryCreateDTO.setName(categoryName);
            this.categoryService.addCategory(categoryCreateDTO);
        }
        List<ProductCreateDTO> products = new ArrayList<>();
 
        products.add(ProductCreateDTO.builder().name("John Deere 6M").category(categoryService.getCategoryById(1L)).image("https://i.ibb.co/7Xjsth5/6110M.png").stock(3).price(BigDecimal.valueOf(180000)).description("Potenza: 125 kW (170 CV), Capacità serbatoio carburante: 230 litri, Velocità massima: 40 km/h, Opzioni di pneumatici e di carico frontale disponibili").build());
        products.add(ProductCreateDTO.builder().name("John Deere 7R").category(categoryService.getCategoryById(1L)).image("https://i.ibb.co/LkJ5gPm/7r-350-r2g069326-lsc-large-large-ad1754c2b0164dc888db449d1f87bd957bb09461.jpg").stock(3).price(BigDecimal.valueOf(280000)).description("Potenza: 241 kW (330 CV), Capacità serbatoio carburante: 630 litri, Velocità massima: 50 km/h, Opzioni di pneumatici e di pesi anteriori disponibili").build());
        products.add(ProductCreateDTO.builder().name("John Deere S700").category(categoryService.getCategoryById(1L)).image("https://i.ibb.co/7RvgfS6/john-deere-s700-combine.jpg").stock(3).price(BigDecimal.valueOf(350000)).description("Potenza: 399 kW (543 CV), Capacità serbatoio cereali: 14,100 litri, Larghezza di taglio: 9 metri, Opzioni di doppie ruote e di estensioni della trebbia disponibili").build());
        products.add(ProductCreateDTO.builder().name("Fendt 300 Vario").category(categoryService.getCategoryById(2L)).image("https://i.ibb.co/c2fCXs6/LV-FENDT-300-VARIO-1.jpg").stock(3).price(BigDecimal.valueOf(120000)).description("Potenza: 82 kW (112 CV), Capacità serbatoio carburante: 160 litri, Velocità massima: 40 km/h, Opzioni di pneumatici e di carico frontale disponibili").build());
        products.add(ProductCreateDTO.builder().name("Fendt 700 Vario").category(categoryService.getCategoryById(2L)).image("https://i.ibb.co/j44cbjp/large-fendt-700-gen6.jpg").stock(3).price(BigDecimal.valueOf(220000)).description("Potenza: 184 kW (250 CV), Capacità serbatoio carburante: 430 litri, Velocità massima: 50 km/h, Opzioni di pneumatici e di pesi anteriori disponibili").build());
        products.add(ProductCreateDTO.builder().name("New Holland CR 7.90").category(categoryService.getCategoryById(3L)).image("https://i.ibb.co/vv6PjG0/mietitrebbie-cr-7-90-scr-new-holland-1.webp").stock(3).price(BigDecimal.valueOf(350000)).description("Potenza: 330 kW (449 CV), Capacità serbatoio cereali: 12,330 litri, Larghezza di taglio: 7.5 metri, Opzioni di doppie ruote e di estensioni della trebbia disponibili").build());
        products.add(ProductCreateDTO.builder().name("New Holland T5").category(categoryService.getCategoryById(3L)).image("https://i.ibb.co/4Z6ryVN/t5-dynamic-auto-command-the-blue-driving-experience.webp").stock(3).price(BigDecimal.valueOf(100000)).description("Potenza: 81 kW (110 CV), Capacità serbatoio carburante: 150 litri, Velocità massima: 40 km/h, Opzioni di pneumatici e di carico frontale disponibili").build());
        products.add(ProductCreateDTO.builder().name("Laverda M300 MCS").category(categoryService.getCategoryById(4L)).image("https://i.ibb.co/r7kSsfg/LAVERDA-M300-Powerflow-Copia.jpg").stock(3).price(BigDecimal.valueOf(120000)).description("Potenza: 210 kW (286 CV), Capacità serbatoio cereali: 6,200 litri, Larghezza di taglio: 5.4 metri, Opzioni di doppie ruote e di estensioni della trebbia disponibili").build());
        products.add(ProductCreateDTO.builder().name("Case IH Quadtrac 620 ").category(categoryService.getCategoryById(5L)).image("https://i.ibb.co/37wV9QX/50140202-61cc-87cb.jpg").stock(3).price(BigDecimal.valueOf(350000)).description("Potenza: 455 kW (620 CV), Capacità serbatoio carburante: 1,300 litri, Velocità massima: 40 km/h, Opzioni di pneumatici e di carico frontale disponibili").build());
        for(ProductCreateDTO product: products) {
            this.productService.addProduct(product);
        }
        RegisterRequest admin = new RegisterRequest("Eugenio","Macchione","admin@email.com","1234");
        this.authenticationService.registerAdminDemo(admin);
        return ResponseEntity.ok().build();
    }
}
