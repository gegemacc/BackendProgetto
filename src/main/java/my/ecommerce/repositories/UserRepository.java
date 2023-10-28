package my.ecommerce.repositories;

import my.ecommerce.entities.User;
import my.ecommerce.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    Page<User> findAllByRoleEquals(Role role, Pageable pageable);
}
