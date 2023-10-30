package my.ecommerce.repositories;

import my.ecommerce.entities.Product;
import my.ecommerce.entities.User;
import my.ecommerce.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    Optional<Product> findById(Long id);
    Page<Product> findAll(Pageable pageable);
}
