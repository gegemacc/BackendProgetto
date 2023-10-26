package my.ecommerce.services;

import my.ecommerce.entities.User;
import my.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User saveUser(@RequestBody User u) {
        return userRepository.save(u);
    }
}
