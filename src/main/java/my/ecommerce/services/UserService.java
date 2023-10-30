package my.ecommerce.services;

import my.ecommerce.dtos.UserDTO;
import my.ecommerce.entities.User;
import my.ecommerce.enums.Role;
import my.ecommerce.repositories.UserRepository;
import my.ecommerce.security.ChangePasswordRequest;
import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<UserDTO> findAll(int page, int size) {
        return userRepository.findAllByRoleEquals(Role.USER, PageRequest.of(page, size)).map(this::mapToDto);
    }

    private UserDTO mapToDto(User user) {
        return modelMapper.map(user,UserDTO.class);
    }

}
