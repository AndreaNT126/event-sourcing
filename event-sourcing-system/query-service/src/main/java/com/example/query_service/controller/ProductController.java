package com.example.query_service.controller;

import com.example.query_service.entity.Product;
import com.example.query_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") UUID id) {
        // Logic to retrieve product by ID will go here.
        Product product = productService.findById(id);
        // For now, returning a placeholder response.
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/search")
    public ResponseEntity<Product> searchProductByName(@RequestParam(name = "nameProduct") String name) {
        // Logic to search product by name will go here.
        Product product = productService.SearchByName(name);
        // For now, returning a placeholder response.
        return ResponseEntity.ok().body(product);
    }

}
