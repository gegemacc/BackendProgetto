package my.ecommerce.controllers;

import lombok.RequiredArgsConstructor;
import my.ecommerce.dtos.UserDTO;
import my.ecommerce.enums.Role;
import my.ecommerce.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@CrossOrigin
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserService userService;

    public ResponseEntity<Page<UserDTO>> getUsers(Pageable pageable, @RequestParam Role role) throws IllegalStateException {
        return ResponseEntity.ok().body(userService.findAll(role,pageable).map(UserDTO::from));
    }
}
