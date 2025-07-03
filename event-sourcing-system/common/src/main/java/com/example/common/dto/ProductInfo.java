package com.example.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo {
    private String name;
    private String description;
    private int quantity;
    private double price;
    private String imageUrl;
    private int category;
}
