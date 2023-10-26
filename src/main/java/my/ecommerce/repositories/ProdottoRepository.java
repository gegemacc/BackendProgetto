package my.ecommerce.repositories;

import my.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoRepository extends JpaRepository<Product,Long> {
    Product findById(long id);
    boolean deleteById(long id);
}
