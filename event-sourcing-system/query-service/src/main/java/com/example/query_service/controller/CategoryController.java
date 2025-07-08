package com.example.query_service.controller;

import com.example.common.dto.ApiResponse;
import com.example.query_service.entity.Category;
import com.example.query_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/search")
    public ApiResponse<?> searchCategoryByName(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable, @RequestParam(value = "name",required = false) String name) {
        return ApiResponse.builder()
                .code(HttpStatus.OK.value())
                .result(categoryService.searchByName(pageable, name))
                .build();
    }
}
