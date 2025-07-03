package com.example.command_service.api.controller;

import com.example.command_service.api.request.CategoryRequest;
import com.example.command_service.category.CategoryService;
import com.example.command_service.core.http.ETag;
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
@RequestMapping("/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> create(@RequestBody @Validated CategoryRequest.CategoryCreated request) throws URISyntaxException {

        UUID categoryId = UUID.randomUUID();

        var result = categoryService.create(categoryId,request.name());
        // Logic to create a category
        return ResponseEntity
                .created(new URI("v1/categories/%s".formatted(categoryId)))
                .eTag(result.value())
                .build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> update(@PathVariable("id") UUID categoryId, @RequestBody @Validated CategoryRequest.CategoryUpdated request,
        @RequestHeader(name = HttpHeaders.IF_MATCH) @NotNull ETag ifMatch) {
        // Logic to update a category
        var etag = categoryService.update(categoryId, request.name(),ifMatch.toLong());
        return ResponseEntity
                .accepted()
                .eTag(etag.value())
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@PathVariable("id") UUID categoryId,
                                       @RequestHeader(name = HttpHeaders.IF_MATCH) @NotNull ETag ifMatch) {
        // Logic to update a category
        var etag = categoryService.delete(categoryId,ifMatch.toLong());
        return ResponseEntity
                .noContent()
                .eTag(etag.value())
                .build();
    }

}
