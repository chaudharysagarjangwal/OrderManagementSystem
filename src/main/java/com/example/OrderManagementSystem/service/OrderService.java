package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.KafkaProducer.OrderProducer;
import com.example.OrderManagementSystem.dto.OrderRequestDto;
import com.example.OrderManagementSystem.dto.OrderResponseDto;
import com.example.OrderManagementSystem.exception.InsufficientStockException;
import com.example.OrderManagementSystem.model.Orders;
import com.example.OrderManagementSystem.model.Product;
import com.example.OrderManagementSystem.reposistory.OrderReposistory;
import com.example.OrderManagementSystem.reposistory.ProductReposistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderReposistory orderReposistory;
    @Autowired
    private ProductReposistory productReposistory;
    @Autowired
    private OrderProducer orderProducer;

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) {
        Product product = productReposistory.findById(orderRequestDto.getProductId()).orElseThrow(() -> new InsufficientStockException("Product not found"));
        //check quantity available or not
        if (orderRequestDto.getQuantity()>product.getQuantity()){
            throw new InsufficientStockException("Not Enough Stock");
        }
        //reduce stock
        product.setQuantity(product.getQuantity()- orderRequestDto.getQuantity());
        productReposistory.save(product);

        Orders orders=new Orders();
        orders.setProductId(product.getId());
        orders.setQuantity(product.getQuantity());
        orders.setTotalPrice(product.getPrice()*orderRequestDto.getQuantity());

        Orders saved=orderReposistory.save(orders);
        //kafka producer
        orderProducer.sendOrderEvent("Order created with ID: "+saved.getId());

        OrderResponseDto res=new OrderResponseDto();
        res.setOrderId(saved.getId());
        res.setTotalPrice(saved.getTotalPrice());
        return res;

    }
}
