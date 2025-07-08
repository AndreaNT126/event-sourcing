package com.example.common.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo {
    private String name;
    private String description;
    private int quantity;
    private double price;
    private String imageUrl;
    private UUID category;
}
