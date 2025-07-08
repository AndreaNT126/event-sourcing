package com.example.query_service.service.Impl;

import com.example.common.dto.PageResponse;
import com.example.query_service.entity.Category;
import com.example.query_service.repository.CategoryRepo;
import com.example.query_service.repository.specification.CategorySpecification;
import com.example.query_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepository;
    private final CategorySpecification categorySpecification;

    @Override
    public Category findById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + id));
    }

    @Override
    public PageResponse<Object> searchByName(Pageable page, String name) {
        Specification<Category> specification = Specification.where(null);

        specification = specification.and(categorySpecification.hasLikeName(name));

        Page<Category> categoryPage = categoryRepository.findAll(specification, page);
        return PageResponse.builder()
                .items(categoryPage.getContent())
                .page(categoryPage.getNumber())
                .size(categoryPage.getSize())
                .items(categoryPage.getContent())
                .build();
    }
}
