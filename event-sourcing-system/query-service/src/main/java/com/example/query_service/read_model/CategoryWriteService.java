package com.example.query_service.read_model;

import com.example.query_service.entity.Category;
import com.example.query_service.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryWriteService {
    private final CategoryRepo categoryRepository;

    public void save(UUID id, String name) {
        categoryRepository.save(
                Category.builder()
                        .id(id)
                        .name(name)
                        .build()
        );
    }

    public void update(UUID id, String name) {
        Category category = findById(id);
        if (category != null) {
            category.setName(name);
            categoryRepository.save(category);
        }
    }

    public void delete(UUID id) {
        categoryRepository.deleteById(id);
    }

    private Category findById(UUID id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
