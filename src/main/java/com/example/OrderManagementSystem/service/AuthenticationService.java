package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.Utils.JwtUtil;
import com.example.OrderManagementSystem.dto.LoginRequestDto;
import com.example.OrderManagementSystem.dto.LoginResponseDto;
import com.example.OrderManagementSystem.dto.RegisterRequestDto;
import com.example.OrderManagementSystem.model.User;
import com.example.OrderManagementSystem.reposistory.UserReposistory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserReposistory userReposistory;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    public User registerNormalUser(@Valid RegisterRequestDto registerRequestDto) {
        if (userReposistory.findByUsername(registerRequestDto.getName()).isPresent()) {
            throw new RuntimeException("User already registered");
        }
        Set<String> roles=new HashSet<String>();
        roles.add("ROLE_USER");
        User user=new User();
        user.setName(registerRequestDto.getName());
        user.setEmail(registerRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        user.setRoles(roles);
        return userReposistory.save(user);
    }

    public LoginResponseDto login(@Valid LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(),loginRequestDto.getPassword())
        );
        User user=userReposistory.findByUsername(loginRequestDto.getEmail())
                .orElseThrow(()->new RuntimeException("User Not found"));
        String token=jwtUtil.generateToken(String.valueOf(user));
        return LoginResponseDto.builder()
                .token(token)
                .username(user.getEmail())
                .roles(user.getRoles()).build();

    }
}
