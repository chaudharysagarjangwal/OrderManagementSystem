package com.example.OrderManagementSystem.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class OrderRequestDto {
    private Long productId;
    private int quantity;

    public OrderRequestDto(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }


    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
