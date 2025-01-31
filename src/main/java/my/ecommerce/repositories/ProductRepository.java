package my.ecommerce.repositories;

import my.ecommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    Optional<Product> findById(Long id);
    Page<Product> findAll(Pageable pageable);

    Page<Product> findAllByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String searchKey1, String searchKey2, Pageable pageable);

    List<Product> findAllByCategoryId(Long id);
}
