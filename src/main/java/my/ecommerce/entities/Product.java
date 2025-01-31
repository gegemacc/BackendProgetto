package my.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.ecommerce.enums.ProductStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @DecimalMin(value = "0", inclusive = false)
    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String image;

    @Min(value = 0)
    @Column(nullable = false)
    private int stock;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus status;

    @Column(columnDefinition = "text")
    private String description;

    @Column(nullable = false, updatable = false)
    private LocalDateTime created;

    private LocalDateTime updated;

    @Version
    private Long version;
}
