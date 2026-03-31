package com.example.OrderManagementSystem.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Product {
    private Long id;
    private String name;
    private double price;
    private int quantity;
}
