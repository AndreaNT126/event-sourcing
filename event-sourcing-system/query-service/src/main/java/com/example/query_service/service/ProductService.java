package com.example.query_service.service;

import com.example.query_service.entity.Product;

import java.util.UUID;

public interface ProductService {
    Product findById(UUID id);
    Product SearchByName(String name);
}
