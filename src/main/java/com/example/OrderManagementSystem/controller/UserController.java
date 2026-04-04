package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.dto.UserRequestDto;
import com.example.OrderManagementSystem.dto.UserResponseDto;
import com.example.OrderManagementSystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto){
        UserResponseDto savedUser = userService.saveUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        List<UserResponseDto> users = userService.getAllUsers();

        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(users);
    }
    @GetMapping("getuserbyid/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id){
       UserResponseDto user= userService.getUserById(id);


        return ResponseEntity.ok(user);
    }
}
