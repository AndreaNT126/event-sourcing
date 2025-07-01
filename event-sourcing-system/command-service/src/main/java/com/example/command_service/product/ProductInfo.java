package com.example.command_service.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo {
    private String name;
    private String description;
    private int quantity;
    private double price;
    private String imageUrl;
    private String category;
}
