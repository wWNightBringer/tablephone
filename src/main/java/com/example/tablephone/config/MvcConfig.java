package com.example.tablephone.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/verify").setViewName("verify");
        registry.addViewController("/").setViewName("verify");
        registry.addViewController("/verify/registration").setViewName("registration");
        registry.addViewController("/phonebook").setViewName("phonebook");
    }
}
