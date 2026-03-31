package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.dto.ProductRequestDto;
import com.example.OrderManagementSystem.dto.ProductResponseDto;
import com.example.OrderManagementSystem.dto.UserResponseDto;
import com.example.OrderManagementSystem.model.Product;
import com.example.OrderManagementSystem.reposistory.ProductReposistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductReposistory productReposistory;
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product=new Product();
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setQuantity(productRequestDto.getQuantity());
        Product save = productReposistory.save(product);
        return new ProductResponseDto(save.getId(), save.getName(), save.getPrice());

    }

    public List<ProductResponseDto> getAllProduct() {
        List<Product> list=productReposistory.findAll();
        return list.stream()
                .map( product-> new ProductResponseDto(
                        product.getId(),
                        product.getName(),
                        product.getPrice()
                ) ).toList();
    }
}
