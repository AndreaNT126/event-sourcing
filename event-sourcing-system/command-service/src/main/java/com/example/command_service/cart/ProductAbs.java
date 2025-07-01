package com.example.command_service.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAbs {
    protected UUID Id;
    protected String name;
    protected String description;
    protected int quantity;
    protected double price;
    protected String imageUrl;
    protected String category;
}
