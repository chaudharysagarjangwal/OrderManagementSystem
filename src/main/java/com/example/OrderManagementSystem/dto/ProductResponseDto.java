package com.example.OrderManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class ProductResponseDto {

    private Long id;
    @NotBlank
    private String name;
    private double price;


    public ProductResponseDto(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public ProductResponseDto() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
