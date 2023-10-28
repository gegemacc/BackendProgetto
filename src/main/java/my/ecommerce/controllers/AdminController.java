package my.ecommerce.controllers;

import lombok.RequiredArgsConstructor;
import my.ecommerce.entities.User;
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
    @GetMapping("/users")
    public ResponseEntity<Page<User>> getUsers(Pageable pageable, @RequestParam Role role) throws IllegalStateException {
        return ResponseEntity.ok().body(userService.findAll(role,pageable));
    }
}
