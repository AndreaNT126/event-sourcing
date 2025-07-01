package com.example.command_service.price;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;

@Configuration
public class PricingConfig {
    @Bean
    @ApplicationScope
    ProductPriceCalculator productPriceCalculator() {
        return new RandomProductPriceCalculator();
    }
}
