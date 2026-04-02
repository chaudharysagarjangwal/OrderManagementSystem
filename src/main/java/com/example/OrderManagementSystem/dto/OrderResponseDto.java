package com.example.OrderManagementSystem.dto;

import lombok.Data;

@Data
public class OrderResponseDto {
    private Long orderId;
    private double totalPrice;
}
