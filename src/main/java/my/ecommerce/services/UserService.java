package my.ecommerce.services;

import my.ecommerce.dtos.UserDTO;
import my.ecommerce.entities.User;
import my.ecommerce.enums.Role;
import my.ecommerce.repositories.UserRepository;
import my.ecommerce.security.ChangePasswordRequest;
import java.security.Principal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public User saveUser(@RequestBody User u) {
        return userRepository.save(u);
    }

     public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);
    }


    public List<UserDTO> findAllUsers() {
        return userRepository.findAllByRoleEquals(Role.USER).stream().map(this::mapToDto).toList();
    }

    private UserDTO mapToDto(User user) {
        return modelMapper.map(user,UserDTO.class);
    }

}
