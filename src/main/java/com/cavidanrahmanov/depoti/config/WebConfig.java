package com.cavidanrahmanov.depoti.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // "uploads" qovluğunu "http://localhost:8080/uploads/" olaraq göstərir
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}

