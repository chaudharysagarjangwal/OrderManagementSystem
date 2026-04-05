package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.dto.ProductRequestDto;
import com.example.OrderManagementSystem.dto.ProductResponseDto;
import com.example.OrderManagementSystem.dto.UserResponseDto;
import com.example.OrderManagementSystem.model.Product;
import com.example.OrderManagementSystem.reposistory.ProductReposistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductReposistory productReposistory;
    @Caching(evict = {
            @CacheEvict(value = "products", allEntries = true),
            @CacheEvict(value = "product", allEntries = true)
    })
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product=new Product();
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setQuantity(productRequestDto.getQuantity());
        Product save = productReposistory.save(product);
        return new ProductResponseDto(save.getId(), save.getName(), save.getPrice());

    }
    @Cacheable(value = "products")
    public List<ProductResponseDto> getAllProduct() {
        System.out.println("Fetching from db");
        List<Product> list=productReposistory.findAll();
        return list.stream()
                .map( product-> new ProductResponseDto(
                        product.getId(),
                        product.getName(),
                        product.getPrice()
                ) ).toList();
    }
    @Cacheable(value = "product", key = "#id")
    public ProductResponseDto getById(Long id) {
        System.out.println("Fetching product from db");

        Product p = productReposistory.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return new ProductResponseDto(
                p.getId(),
                p.getName(),
                p.getPrice()
        );
    }
}
