package com.example.query_service.controller;

import com.example.query_service.entity.Category;
import com.example.query_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/categories")
public class CategoryController {
    // This controller will handle HTTP requests related to categories.
    // Currently, it does not have any endpoints defined.
    // You can add methods here to handle CRUD operations for categories.
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") UUID id) {
        // Logic to retrieve category by ID will go here.
        // For now, returning a placeholder response.
        return ResponseEntity.ok(categoryService.findById(id));
    }
}
