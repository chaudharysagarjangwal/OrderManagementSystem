package com.example.OrderManagementSystem.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;
@Data

public class LoginResponseDto {
    private String token;
    private String username;
    private Set<String> roles;

    public LoginResponseDto(String token, String username, Set<String> roles) {
        this.token = token;
        this.username = username;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
