package com.example.OrderManagementSystem.reposistory;

import com.example.OrderManagementSystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReposistory extends JpaRepository<Product,Long> {
}
