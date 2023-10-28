package my.ecommerce.repositories;

import my.ecommerce.entities.Product;
import my.ecommerce.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
