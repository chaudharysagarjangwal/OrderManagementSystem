package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.dto.UserRequestDto;
import com.example.OrderManagementSystem.dto.UserResponseDto;
import com.example.OrderManagementSystem.model.User;
import com.example.OrderManagementSystem.reposistory.UserReposistory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserReposistory userReposistory;
    public UserResponseDto saveUser(UserRequestDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        User savedUser = userReposistory.save(user);

        return new UserResponseDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }


    public List<UserResponseDto> getAllUsers() {
        List<User> all = userReposistory.findAll();

        return all.stream()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getName(),
                        user.getEmail()
                ))
                .toList();
    }

    public UserResponseDto getUserById(Long id) {
        // Fetch user from repository
        User user = userReposistory.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Convert entity to DTO
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
