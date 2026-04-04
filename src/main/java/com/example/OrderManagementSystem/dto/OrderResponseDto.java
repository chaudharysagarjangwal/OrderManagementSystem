package com.example.OrderManagementSystem.dto;

import lombok.Data;

@Data
public class OrderResponseDto {
    private Long orderId;
    private double totalPrice;

    public OrderResponseDto(Long orderId, double totalPrice) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
    }

    public OrderResponseDto() {

    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
