package com.example.query_service.repository.specification;

import com.example.query_service.entity.Category;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CategorySpecification {

    public Specification<Category> hasLikeName(String name) {

        if (name == null || name.trim().isEmpty()) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        }

        String lowerCaseName = name.toLowerCase();
        String keyword = "%" + lowerCaseName + "%";
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), keyword);
    }
}
