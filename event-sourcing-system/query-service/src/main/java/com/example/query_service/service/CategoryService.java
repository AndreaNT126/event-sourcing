package com.example.query_service.service;

import com.example.query_service.entity.Category;

import java.util.UUID;

public interface CategoryService {
    Category findById(UUID id);
}
