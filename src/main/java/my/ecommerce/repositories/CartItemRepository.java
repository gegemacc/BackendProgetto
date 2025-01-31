package my.ecommerce.repositories;

import my.ecommerce.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findById(Long id);

    CartItem findByProductId(Long productId);

}
