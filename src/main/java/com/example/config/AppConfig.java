package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"com.example.service", "com.example.dao", "com.example.controller"})
@Import({HibernateConfig.class, SecurityConfig.class, WebConfig.class})
public class AppConfig {
}