package com.example.query_service.read_model;

import com.example.query_service.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryWriteService {
    private final CategoryRepo categoryRepository;


}
