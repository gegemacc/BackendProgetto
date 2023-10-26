package my.ecommerce.controllers;

import lombok.RequiredArgsConstructor;
import my.ecommerce.security.AuthenticationRequest;
import my.ecommerce.security.AuthenticationResponse;
import my.ecommerce.security.RegisterRequest;
import my.ecommerce.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping("/logged")
    public ResponseEntity<String> isLogged() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}