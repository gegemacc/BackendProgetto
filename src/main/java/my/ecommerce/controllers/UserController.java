package my.ecommerce.controllers;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import my.ecommerce.security.ChangePasswordRequest;
import my.ecommerce.services.UserService;

@RestController
@RequestMapping
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    @PutMapping("/profile/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

}
