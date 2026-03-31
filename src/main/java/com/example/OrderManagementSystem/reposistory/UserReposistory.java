package com.example.OrderManagementSystem.reposistory;

import com.example.OrderManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReposistory extends JpaRepository<User,Long> {
}
