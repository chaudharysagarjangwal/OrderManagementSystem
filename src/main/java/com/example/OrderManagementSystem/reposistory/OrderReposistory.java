package com.example.OrderManagementSystem.reposistory;

import com.example.OrderManagementSystem.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderReposistory extends JpaRepository<Orders,Long> {
}
