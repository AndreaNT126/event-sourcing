package com.example.query_service.service.Impl;

import com.example.query_service.entity.Product;
import com.example.query_service.repository.ProductRepo;
import com.example.query_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;

    @Override
    public Product findById(UUID id) {
        return productRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
    }

    @Override
    public Product SearchByName(String name) {
        return productRepo.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with name: " + name));
    }

    // Inject the necessary repositories and services here
    // For example:
    // private final CategoryRepo categoryRepo;

    // Constructor injection can be used to initialize the repository
    // public ProductServiceImpl(CategoryRepo categoryRepo) {
    //     this.categoryRepo = categoryRepo;
    // }

    // Implement methods from ProductService interface here
}
