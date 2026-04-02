package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.dto.ProductRequestDto;
import com.example.OrderManagementSystem.dto.ProductResponseDto;
import com.example.OrderManagementSystem.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto){
       ProductResponseDto product= productService.createProduct(productRequestDto);
       return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>>  getAllProducts(){
       List<ProductResponseDto> allProducts= productService.getAllProduct();
       if (allProducts.isEmpty()){
           return ResponseEntity.noContent().build();
       }
       return ResponseEntity.ok(allProducts);
    }
}
