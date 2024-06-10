package com.codegym.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableAutoConfiguration
public class AppConfiguration implements WebMvcConfigurer {

    private final long MAX_AGE_SECS = 3600;

    @Value("http://localhost:5500")
    private String[] allowedOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .allowCredentials(false)
                .maxAge(MAX_AGE_SECS);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }



}
