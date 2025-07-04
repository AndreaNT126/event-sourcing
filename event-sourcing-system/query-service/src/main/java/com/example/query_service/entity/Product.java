package com.example.query_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    private UUID id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
