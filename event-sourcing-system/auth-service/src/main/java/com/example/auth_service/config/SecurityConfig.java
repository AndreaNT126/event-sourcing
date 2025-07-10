package com.example.auth_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain clientSecurityFilterChain(HttpSecurity http) throws Exception {
           // Configure your security filter chain here
        http.authorizeHttpRequests(authz -> authz.requestMatchers("/", "/login**").permitAll().anyRequest().authenticated())
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true))
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .permitAll());

        return http.build(); // Replace with actual configuration
    }

    @Bean
    public SecurityFilterChain resourceSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/products/**").hasAuthority("SCOPE_read")
                        .requestMatchers("/orders/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwkSetUri("http://localhost:8080/.well-known/jwks.json")));

        return http.build();
    }
}
