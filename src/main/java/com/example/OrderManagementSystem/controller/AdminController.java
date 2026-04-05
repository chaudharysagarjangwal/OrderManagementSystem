package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.dto.RegisterRequestDto;
import com.example.OrderManagementSystem.model.User;
import com.example.OrderManagementSystem.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
//@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/registeradminuser")
    public ResponseEntity<User> registerAdminUser(@Valid @RequestBody RegisterRequestDto registerRequestDto){
        return ResponseEntity.ok(authenticationService.registerAdminUser(registerRequestDto));

    }
}
