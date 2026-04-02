package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.dto.OrderRequestDto;
import com.example.OrderManagementSystem.dto.OrderResponseDto;
import com.example.OrderManagementSystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping
    public ResponseEntity<OrderResponseDto> placeOrder(@RequestBody OrderRequestDto orderRequestDto) {

        OrderResponseDto response = orderService.placeOrder(orderRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
