package my.ecommerce.repositories;


import my.ecommerce.entities.User;
import my.ecommerce.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    List<User> findAllByRoleEquals(Role role);
}
