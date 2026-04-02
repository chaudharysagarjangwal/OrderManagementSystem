package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.dto.LoginRequestDto;
import com.example.OrderManagementSystem.dto.LoginResponseDto;
import com.example.OrderManagementSystem.dto.RegisterRequestDto;
import com.example.OrderManagementSystem.model.User;
import com.example.OrderManagementSystem.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/registernormaluser")
    public ResponseEntity<User> registerNormalUser(@Valid @RequestBody RegisterRequestDto registerRequestDto){
        return ResponseEntity.ok(authenticationService.registerNormalUser(registerRequestDto));
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authenticationService.login(loginRequestDto));
    }
}
