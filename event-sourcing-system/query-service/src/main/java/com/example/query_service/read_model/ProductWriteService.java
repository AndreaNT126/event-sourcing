package com.example.query_service.read_model;

import com.example.common.event.ProductEvent;
import com.example.query_service.entity.Category;
import com.example.query_service.entity.Product;
import com.example.query_service.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductWriteService {
    private final ProductRepo productRepository;

    public void save(ProductEvent.ProductEventCreated eventCreated) {
        Product product = Product.builder()
                .id(eventCreated.productId())
                .name(eventCreated.productInfo().getName())
                .description(eventCreated.productInfo().getDescription())
                .quantity(eventCreated.productInfo().getQuantity())
                .price(eventCreated.productInfo().getPrice())
                .imageUrl(eventCreated.productInfo().getImageUrl())
                .category(Category.builder().id(eventCreated.productInfo().getCategory()).build())
                .build();

        productRepository.save(product);
    }

    public void update(ProductEvent.ProductEventUpdated eventUpdated) {
        Product product = findById(eventUpdated.productId());

        product.setName(eventUpdated.productInfo().getName());
        product.setDescription(eventUpdated.productInfo().getDescription());
        product.setQuantity(eventUpdated.productInfo().getQuantity());
        product.setPrice(eventUpdated.productInfo().getPrice());
        product.setImageUrl(eventUpdated.productInfo().getImageUrl());
        product.setCategory(Category.builder().id(eventUpdated.productInfo().getCategory()).build());

        productRepository.save(product);
    }

    public void delete(ProductEvent.ProductEventDeleted eventDeleted) {
        productRepository.deleteById(eventDeleted.productId());
    }

    public Product findById(UUID id) {
        return productRepository.findById(id).orElse(null);
    }
}
