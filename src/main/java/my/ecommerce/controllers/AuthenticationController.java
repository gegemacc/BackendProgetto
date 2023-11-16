package my.ecommerce.controllers;

import lombok.RequiredArgsConstructor;
import my.ecommerce.entities.User;
import my.ecommerce.enums.Role;
import my.ecommerce.security.AuthenticationRequest;
import my.ecommerce.security.AuthenticationResponse;
import my.ecommerce.security.RegisterRequest;
import my.ecommerce.security.RoleResponse;
import my.ecommerce.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }


}