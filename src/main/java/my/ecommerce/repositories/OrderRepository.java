package my.ecommerce.repositories;

import my.ecommerce.entities.Order;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAll();
    List<Order> findAllByUserId(Long userId);
}
