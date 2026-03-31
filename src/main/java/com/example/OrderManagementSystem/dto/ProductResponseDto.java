package com.example.OrderManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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


}
