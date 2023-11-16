package my.ecommerce.repositories;

import my.ecommerce.entities.Category;
import my.ecommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>  {

    Optional<Category> findById(Long id);
    List<Category> findAll();
}
