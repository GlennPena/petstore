package com.pena.petstore.config;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
  @Value("${app.cors.allowed-origins:http://localhost:5173,http://localhost:5174,http://localhost:5175}")
  private String allowedOrigins;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    String[] origins = Arrays.stream(allowedOrigins.split(","))
        .map(String::trim)
        .toArray(String[]::new);

    var mapping = registry.addMapping("/pena/**")
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
        .allowedHeaders("*")
        .allowCredentials(false)
        .maxAge(3600);

    if (origins.length == 1 && "*".equals(origins[0])) {
      mapping.allowedOriginPatterns("*");
      return;
    }

    mapping.allowedOrigins(origins)
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
        .allowedHeaders("*")
        .allowCredentials(false)
        .maxAge(3600);
  }
}
