package com.example.query_service.repository;

import com.example.query_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepo extends JpaRepository<Product, UUID> {
    // Additional query methods can be defined here if needed
    Optional<Product> findByName(String name);
}
