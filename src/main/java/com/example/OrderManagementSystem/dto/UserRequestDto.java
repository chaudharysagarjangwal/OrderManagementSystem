package com.example.OrderManagementSystem.dto;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class UserRequestDto {
    @NotBlank
    private String name;
    @Email
    private String email;
    @Size(min = 4)
    private  String password;

//    public Set<String> getRoles() {
//        return roles;
//    }

//    public void setRoles(Set<String> roles) {
//        this.roles = roles;
//    }
//
//    @ElementCollection(fetch = FetchType.EAGER)
//    private Set<String> roles;
////

    public UserRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
