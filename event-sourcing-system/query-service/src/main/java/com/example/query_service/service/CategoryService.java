package com.example.query_service.service;

import com.example.common.dto.PageResponse;
import com.example.query_service.entity.Category;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CategoryService {
    Category findById(UUID id);
    PageResponse<Object> searchByName(Pageable page, String name);
}
