package com.example.command_service.api.controller;

import com.example.command_service.api.request.ProductRequest;
import com.example.command_service.core.http.ETag;
import com.example.command_service.product.ProductService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Void> createProduct(@Validated @RequestBody ProductRequest.ProductCreated request) throws URISyntaxException {
        UUID productId = UUID.randomUUID();

        var result = productService.create(productId,request.productInfo());

        return ResponseEntity
                .created(new URI("v1/products/%s".formatted(productId)))
                .eTag(result.value())
                .build();
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ResponseEntity<Void> updateProduct(
            @Validated @RequestBody ProductRequest.ProductUpdated request,
            @RequestHeader(name = HttpHeaders.IF_MATCH) @NotNull ETag ifMatch) {
        var result = productService.update(request.productId(), request.productInfo(), ifMatch.toLong());

        return ResponseEntity
                .accepted()
                .eTag(result.value())
                .build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") UUID id,
                                @RequestHeader(name = HttpHeaders.IF_MATCH) @NotNull ETag ifMatch) {
        var result = productService.delete(id, ifMatch.toLong());

        return ResponseEntity
                .ok()
                .eTag(result.value())
                .build();
    }
}
