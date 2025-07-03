package com.example.query_service.config;

import com.example.common.event.ProductEvent;
import com.example.query_service.entity.Product;
import org.modelmapper.ExpressionMap;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelmapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper;
    }



}
