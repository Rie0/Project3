package org.twspring.project3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.twspring.project3.DTO.CustomerDTO;
import org.twspring.project3.DTO.EmployeeDTO;
import org.twspring.project3.Model.User;
import org.twspring.project3.Service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    //Only used to add the first admin, just here to make tests easier
//    @PostMapping("/register-admin")
//    public ResponseEntity registerAdmin(@Valid @RequestBody User user) {
//        authService.registerAdmin(user);
//        return ResponseEntity.status(200).body("Admin registered successfully");
//    }
}
